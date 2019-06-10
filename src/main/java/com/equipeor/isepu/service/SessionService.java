package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.payload.request.SessionRequest;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collection;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Collection<SessionResponse> getAllUserSessions(UserPrincipal userPrincipal) {
        return null;
    }

    public Collection<SessionResponse> getAllCourseSessions(String courseName, UserPrincipal userPrincipal) {
        return null;
    }

    public SessionResponse getSession(SessionRequest sessionRequest, UserPrincipal userPrincipal) {
        return null;
    }

    public URI addCourse(SessionRequest sessionRequest, UserPrincipal userPrincipal) {
        return null;
    }

    public void deleteCourse(Long id) {
        sessionRepository.deleteById(id);
    }
}
