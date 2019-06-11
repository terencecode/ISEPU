package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.payload.request.SessionRequest;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.SessionRepository;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
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
            Student currentStudent = student.get();
            return null;
        } else if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            return null;
        } else throw new UserNotFoundException();
    }

    public Collection<SessionResponse> getAllCourseSessions(String courseName, UserPrincipal userPrincipal) {
        return null;
    }

    public SessionResponse getSession(SessionRequest sessionRequest, UserPrincipal userPrincipal) {
        return null;
    }

    public URI addSession(SessionRequest sessionRequest, UserPrincipal userPrincipal) {
        return null;
    }

    public void deleteCourse(Long id) {
        sessionRepository.deleteById(id);
    }
}
