package com.equipeor.isepu.controller;


import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @GetMapping(value = "/Subject")
    public List<Subject> afficherSubject(){
        return subjectService.afficherSubjects();
    }
    @GetMapping(value = "/Subject/{id}")
    public Subject afficherSubjectById(@PathVariable int id){
        return subjectService.afficherSubjectById(id);
    }

    @GetMapping(value = "/Subject/{name}")
    public Subject afficherSubjectByName(@PathVariable String name){
        return subjectService.afficherSubjectByName(name);
    }

    @DeleteMapping(value = "/Subject/{id}")
    public void deleteSubject(@PathVariable int id){
        subjectService.deleteSubject(id);
    }
    @DeleteMapping(value = "/Subject/{name}")
    public void deleteSubjectByName(@PathVariable String name){
        subjectService.deleteByName(name);
    }

    @PutMapping(value = "/Subject")

    public void updateSubject(@RequestBody Subject subject){
        subjectService.updateSubject(subject);
    }

    @PostMapping(value = "/Subject")
    public ResponseEntity<Void> addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }





}
