package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.response.SubjectResponse;
import com.equipeor.isepu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<Collection<SubjectResponse>> getCurrentUserSubjects(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(subjectService.getCurrentUserSubjects(userPrincipal));
    }

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public ResponseEntity<Collection<SubjectResponse>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{name}")
    public ResponseEntity<SubjectResponse> getSubjectByName(@PathVariable String name) {
        return ResponseEntity.ok(subjectService.getSubjectByName(name));
    }

    @Secured("ROLE_PROFESSOR")
    @DeleteMapping(value = "/{name}")
    public void deleteSubject(@PathVariable String name) {
        subjectService.deleteSubject(name);
    }

    @Secured("ROLE_PROFESSOR")
    @PutMapping(value = "/{id}")
    public void updateSubject(@RequestBody Subject subject) {
        subjectService.updateSubject(subject);
    }

    @Secured("ROLE_PROFESSOR")
    @PostMapping
    public ResponseEntity<Void> addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }
}
