package com.equipeor.isepu.controller;


import com.equipeor.isepu.repository.CourseRepository;
import com.equipeor.isepu.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping(value="/Cours")
    public List<Course> listCours() {
        return courseRepository.findAll();
    }

    @GetMapping(value = "/Cours/{Prof}")
    public Course afficherCours(@PathVariable int prof){
        return courseRepository.findDistinctByProfessorId(prof);
    }




    //ajouter un cours
    @PostMapping(value="/Cours")
    public ResponseEntity<Void> ajouterCours(@RequestBody Course coursname){
        Course coursAdded = courseRepository.save(coursname);
        if (coursAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(coursAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
