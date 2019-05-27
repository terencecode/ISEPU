package com.equipeor.isepu.controller;

import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/all")
    public List<Subject> getSubjects() {
        return subjectService.afficherSubjects();
    }

    @GetMapping(value = "/{id}")
    public Subject getSubjectById(@PathVariable int id) {
        return subjectService.afficherSubjectById(id);
    }

    @GetMapping(value = "/{name}")
    public Subject getSubjectByName(@PathVariable String name) {
        return subjectService.afficherSubjectByName(name);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.deleteSubject(id);
    }

    @DeleteMapping(value = "/{name}")
    public void deleteSubjectByName(@PathVariable String name) {
        subjectService.deleteByName(name);
    }

    @PutMapping
    public void updateSubject(@RequestBody Subject subject) {
        subjectService.updateSubject(subject);
    }

    @PostMapping
    public ResponseEntity<Void> addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }
}
