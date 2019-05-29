package com.equipeor.isepu.controller;

import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public List<Subject> getSubjects() {
        return subjectService.afficherSubjects();
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/get/{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.afficherSubjectById(id);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{name}")
    public Subject getSubjectByName(@PathVariable String name) {
        return subjectService.afficherSubjectByName(name);
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
