package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.HomeworkStateToHomeworkResponseConverter;
import com.equipeor.isepu.converter.HomeworkToHomeworkResponseConverter;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.*;
import com.equipeor.isepu.payload.request.AddHomeworkRequest;
import com.equipeor.isepu.payload.request.UpdateHomeworkRequest;
import com.equipeor.isepu.payload.response.HomeworkResponse;
import com.equipeor.isepu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private HomeworkStateRepository homeworkStateRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Collection<HomeworkResponse> getCurrentUserHomeWorks(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (student.isPresent()) {
            Student currentStudent = student.get();
            List<HomeworkState> homeworkStates = homeworkStateRepository.findAllByStudentId(currentStudent.getId());
            return new HomeworkStateToHomeworkResponseConverter(true).createFromEntities(homeworkStates);
        } else if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            List<Homework> homeworks = new ArrayList<>();
            for (Course course : currentProfessor.getCourses()) {
                for (Session session : course.getSessions()) {
                    homeworks.addAll(session.getHomeworks());
                }
            }
            return new HomeworkToHomeworkResponseConverter(true).createFromEntities(homeworks);
        } else throw new UserNotFoundException("The user doesn't seem to be a student");
    }

    public URI addHomework(AddHomeworkRequest homeworkRequest, UserPrincipal userPrincipal) {
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Optional<Course> course = courseRepository.findByName(homeworkRequest.getCourseName());
            if (course.isPresent()) {
                Course currentCourse = course.get();
                if (currentProfessor.getCourses().contains(currentCourse)) {
                    Optional<Session> session = sessionRepository.findById(homeworkRequest.getSessionId());
                    if(session.isPresent()) {
                        Session currentSession = session.get();
                        if (currentCourse.getSessions().contains(currentSession)) {
                            Homework homework = homeworkRepository.save(new Homework(homeworkRequest.getDescription(), currentSession));
                            homeworkRepository.flush();
                            Set<HomeworkState> homeworkStates = new HashSet<>();
                            for (Student student : currentCourse.getStudents()) {
                                homeworkStates.add(new HomeworkState(HomeworkStatus.TO_DO, homework, student));
                            }
                            homework.setHomeworkStates(homeworkStates);
                            homeworkRepository.save(homework);
                            homeworkRepository.flush();
                            return ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(homework.getId())
                                    .toUri();
                        }
                    }
                }
            }
        }
        throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }

    public URI updateHomeworkState(UpdateHomeworkRequest updateHomeworkRequest, UserPrincipal userPrincipal) {
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Optional<Homework> homework = homeworkRepository.findById(updateHomeworkRequest.getId());
            if (homework.isPresent()) {
                Homework updatedHomework = homework.get();
                updatedHomework.setDescription(updateHomeworkRequest.getDescription() == null ? updatedHomework.getDescription() : updateHomeworkRequest.getDescription());
                if (updateHomeworkRequest.getSessionId() != null) {
                    Optional<Session> session = sessionRepository.findById(updateHomeworkRequest.getSessionId());
                    if (session.isPresent()) {
                        updatedHomework.setSession(session.get());
                    }
                }
                homeworkRepository.save(updatedHomework);
                homeworkRepository.flush();
                return ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(updatedHomework.getId())
                        .toUri();
            }
        }
        throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }
}
