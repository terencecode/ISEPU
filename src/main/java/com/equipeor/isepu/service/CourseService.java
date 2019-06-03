package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.CourseRequestToCourseConverter;
import com.equipeor.isepu.converter.CourseToCourseResponseConverter;
import com.equipeor.isepu.exception.CourseNotFoundException;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.request.AddCourseRequest;
import com.equipeor.isepu.payload.response.CourseResponse;
import com.equipeor.isepu.repository.CourseRepository;
import com.equipeor.isepu.repository.ProfessorRepository;
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
    private SubjectRepository subjectRepository;

    public CourseService(){}

    public Collection<CourseResponse> getAllCourses() {
        return new CourseToCourseResponseConverter().createFromEntities(courseRepository.findAll());
    }

    public CourseResponse getCourseByName(String courseName) {
        Optional<Course> course = courseRepository.findByName(courseName);
        if (!course.isPresent())
            throw new CourseNotFoundException();
        else
            return new CourseToCourseResponseConverter().convertFromEntity(course.get());
    }

    public Collection<CourseResponse> getCoursesByProfessorEmail(@PathVariable String professorEmail){
        return new CourseToCourseResponseConverter().createFromEntities(courseRepository.findByProfessorEmail(professorEmail));
    }

    public URI addCourse(@RequestBody AddCourseRequest addCourseRequest, UserPrincipal userPrincipal){
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
            Optional<Subject> subject = Optional.ofNullable(subjectRepository.findByName(addCourseRequest.getSubjectName()));
            if (subject.isPresent()) {
                CourseRequestToCourseConverter converter = new CourseRequestToCourseConverter(subject.get(), professor.get());
                Course course = converter.convertFromEntity(addCourseRequest);
                courseRepository.save(course);
                return ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(course.getId())
                        .toUri();
            } else throw new CourseNotFoundException();
        } else throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public Collection<CourseResponse> getCoursesBySubjectName(String subjectName) {
        return new CourseToCourseResponseConverter().createFromEntities(courseRepository.findBySubjectName(subjectName));
    }
}
