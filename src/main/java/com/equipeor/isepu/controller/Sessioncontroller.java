package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.payload.request.SessionRequest;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/session")
public class Sessioncontroller {

    @Autowired
    private SessionService sessionService;

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public ResponseEntity<Collection<SessionResponse>> getCurrentUserSessions(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(sessionService.getCurrentUserSessions(userPrincipal));
    }

    @Secured("ROLE_USER")
    @GetMapping("/{courseName}")
    public ResponseEntity<Collection<SessionResponse>> getCourseSessions(@PathVariable String courseName, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(sessionService.getAllCourseSessions(courseName, userPrincipal));
    }

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<SessionResponse> getSession(@RequestBody SessionRequest sessionRequest, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(sessionService.getSession(sessionRequest, userPrincipal));
    }

    @Secured("ROLE_PROFESSOR")
    @PostMapping
    public ResponseEntity<Void> addSession(@RequestBody SessionRequest sessionRequest, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.created(sessionService.addSession(sessionRequest, userPrincipal)).build();
    }

    //TODO: addCourse via admin and professorId

    @Secured({"ROLE_PROFESSOR", "ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
