package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.CourseRequestToCourseConverter;
import com.equipeor.isepu.converter.CourseToCourseResponseConverter;
import com.equipeor.isepu.exception.CourseNotFoundException;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.request.AddCourseRequest;
import com.equipeor.isepu.payload.request.RegisterStudentRequest;
import com.equipeor.isepu.payload.response.CourseResponse;
import com.equipeor.isepu.repository.CourseRepository;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.StudentRepository;
import com.equipeor.isepu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public CourseService(){}

    public Collection<CourseResponse> getAllCourses() {
        return new CourseToCourseResponseConverter(true).createFromEntities(courseRepository.findAll());
    }

    public CourseResponse getCourseByName(String courseName) {
        Optional<Course> course = courseRepository.findByName(courseName);
        if (!course.isPresent())
            throw new CourseNotFoundException();
        else
            return new CourseToCourseResponseConverter(true).convertFromEntity(course.get());
    }

    public Collection<CourseResponse> getCoursesByProfessorEmail(@PathVariable String professorEmail){
        return new CourseToCourseResponseConverter(true).createFromEntities(courseRepository.findByProfessorEmail(professorEmail));
    }

    public Collection<CourseResponse> getCurrentUserCourses(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (student.isPresent()) {
            Student currentStudent = student.get();
            return new CourseToCourseResponseConverter(true).createFromEntities(currentStudent.getCourses());
        } else if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            return new CourseToCourseResponseConverter(true).createFromEntities(currentProfessor.getCourses());
        } else throw new UserNotFoundException();
    }

    public URI addCourse(@RequestBody AddCourseRequest addCourseRequest, UserPrincipal userPrincipal){
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
         Optional<Subject> subject = subjectRepository.findByName(addCourseRequest.getSubjectName());
            CourseRequestToCourseConverter converter;
            if (subject.isPresent()) {
                converter = new CourseRequestToCourseConverter(subject.get(), professor.get());
            } else {
                Subject newSubject = new Subject(addCourseRequest.getSubjectName());
                converter = new CourseRequestToCourseConverter(newSubject, professor.get());
                subjectRepository.save(newSubject);
                subjectRepository.flush();
            }
            
            Course course = converter.convertFromEntity(addCourseRequest);
            course = courseRepository.save(course);
            courseRepository.flush();
            return ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(course.getId())
                    .toUri();

        } else throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }

    public void registerStudents(RegisterStudentRequest registerStudentRequest,  UserPrincipal userPrincipal) {
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
            Optional<Course> course =  courseRepository.findByName(registerStudentRequest.getCourseName());
            if (course.isPresent()) {
                Course currentCourse = course.get();
                currentCourse.setStudents(studentRepository.findAllByEmailIn(registerStudentRequest.getStudentEmails()));
                courseRepository.save(currentCourse);
            } else throw new CourseNotFoundException();
        } else throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Collection<CourseResponse> getCoursesBySubjectName(String subjectName) {
        return new CourseToCourseResponseConverter(true).createFromEntities(courseRepository.findBySubjectName(subjectName));
    }
}
