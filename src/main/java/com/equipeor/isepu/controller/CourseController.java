package com.equipeor.isepu.controller;

import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getCourses() {
        return courseService.listCourses();
    }

    @GetMapping(value = "/all/{professor}")
    public List<Course> getCoursesByProfessor(@PathVariable int professor) {
        return courseService.getCourseByProfessor(professor);
    }

    @GetMapping(value = "/{courseName}")
    public List<Course> getCourseByName(@PathVariable String courseName) {
        return courseService.getCourseByName(courseName);

    }

    @GetMapping(value = "/{courseName}/{professor}")
    public Course getCourseByNameAndProf(@PathVariable String courseName, @PathVariable int professor) {
        return courseService.getCourseByNameAndProf(courseName, professor);
    }

    @PostMapping
    public ResponseEntity<Void> addCourse(@RequestBody Course courseName) {
        return courseService.ajouterCourse(courseName);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
