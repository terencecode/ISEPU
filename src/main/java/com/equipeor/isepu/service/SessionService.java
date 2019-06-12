package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.SessionToSessionResponseConverter;
import com.equipeor.isepu.exception.CourseNotFoundException;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Session;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.payload.request.SessionRequest;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.SessionRepository;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Collection<SessionResponse> getCurrentUserSessions(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (student.isPresent()) {
            Collection<Session> sessions = new ArrayList<>();
            Student currentStudent = student.get();
            for(Course course : currentStudent.getCourses()) {
                sessions.addAll(course.getSessions());
            }
            return new SessionToSessionResponseConverter(true).createFromEntities(sessions);
        } else if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Collection<Session> sessions = new ArrayList<>();
            for(Course course : currentProfessor.getCourses()) {
                sessions.addAll(course.getSessions());
            }
            return new SessionToSessionResponseConverter(true).createFromEntities(sessions);
        } else throw new UserNotFoundException();
    }

    public Collection<SessionResponse> getCourseSessions(String courseName, UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (student.isPresent()) {
            Collection<Session> sessions = new ArrayList<>();
            Student currentStudent = student.get();
            for(Course course : currentStudent.getCourses()) {
                if (course.getName() == courseName) {
                    sessions.addAll(course.getSessions());
                }
            }
            if (sessions.isEmpty()) throw new CourseNotFoundException();
            return new SessionToSessionResponseConverter(true).createFromEntities(sessions);
        } else if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Collection<Session> sessions = new ArrayList<>();
            for(Course course : currentProfessor.getCourses()) {
                if (course.getName() == courseName) {
                    sessions.addAll(course.getSessions());
                }
            }
            if (sessions.isEmpty()) throw new CourseNotFoundException();
            return new SessionToSessionResponseConverter(true).createFromEntities(sessions);
        } else throw new UserNotFoundException();
    }

    public URI addSession(SessionRequest sessionRequest, UserPrincipal userPrincipal) {
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Collection<Course> courses = currentProfessor.getCourses();
            for (Course course : courses) {
                if (course.getName() == sessionRequest.getCourseName()) {
                    Session session = new Session(Instant.ofEpochMilli(sessionRequest.getStartingTime()), Instant.ofEpochMilli(sessionRequest.getFinishingTime()), course);
                    Session newSession = sessionRepository.save(session);
                    sessionRepository.flush();
                    return ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(newSession.getId())
                            .toUri();
                }
            }
        }
        throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }

    public URI updateSession(SessionRequest sessionRequest, UserPrincipal userPrincipal) {
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Optional<Session> session = sessionRepository.findSessionById(sessionRequest.getId());
            if (session.isPresent()) {
                Session updatedSession = session.get();
                if (currentProfessor.getCourses().contains(updatedSession.getCourse())) {
                    updatedSession.setStartingTime(sessionRequest.getStartingTime() == null ? updatedSession.getStartingTime() : Instant.ofEpochMilli(sessionRequest.getStartingTime()));
                    updatedSession.setFinishingTime(sessionRequest.getFinishingTime() == null ? updatedSession.getFinishingTime() : Instant.ofEpochMilli(sessionRequest.getFinishingTime()));
                    sessionRepository.save(updatedSession);
                    sessionRepository.flush();
                    return ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(updatedSession.getId())
                            .toUri();
                }
            }

        }
        throw new ProfessorNotFoundException("The current user doesn't seem to be a professor");
    }

    public void deleteCourse(Long id) {
        sessionRepository.deleteById(id);
    }
}
