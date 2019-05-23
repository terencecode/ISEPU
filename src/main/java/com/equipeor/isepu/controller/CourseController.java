package com.equipeor.isepu.controller;



import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping(value="/Course")
    public List<Course> listCours() {
        return courseService.listCourses();
    }

    @GetMapping(value = "/Course/{prof}")
    public List<Course> afficherCours(@PathVariable int prof){
        return courseService.afficherCourse(prof);
    }


    @GetMapping(value = "/Course/{courseName}")
    public List<Course> afficherCourseByName(@PathVariable String courseName){
        return courseService.afficherCourseByName(courseName);

    }

    @GetMapping(value = "/Course/{courseName}/{prof}")
    public Course afficherCourseByNameAndProf(@PathVariable String courseName,@PathVariable int prof){
        return courseService.afficherCourseByNameAndProf(courseName,prof);
    }





    //ajouter un cours
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/Course")
    public ResponseEntity<Void> ajouterCours(@RequestBody Course coursname){
        return courseService.ajouterCourse(coursname);

    }


    @DeleteMapping(value = "/Course/{id}")
    public void deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
    }


}
