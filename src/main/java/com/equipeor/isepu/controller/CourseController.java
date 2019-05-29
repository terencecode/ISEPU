package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.payload.request.AddCourseRequest;
import com.equipeor.isepu.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Secured("ROLE_USER")
    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/all/{professor}")
    public List<Course> getCoursesByProfessor(@PathVariable int professor) {
        return courseService.getCourseByProfessor(professor);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{courseName}")
    public List<Course> getCourseByName(@PathVariable String courseName) {
        return courseService.getCourseByName(courseName);

    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{courseName}/{professor}")
    public Course getCourseByNameAndProf(@PathVariable String courseName, @PathVariable int professor) {
        return courseService.getCourseByNameAndProf(courseName, professor);
    }

    @Secured("ROLE_PROFESSOR")
    @PostMapping
    public ResponseEntity<Void> addCourse(@RequestBody AddCourseRequest courseName, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.created(courseService.addCourse(courseName, userPrincipal)).build();
    }

    //TODO: addCourse via admin and professorId

    @Secured({"ROLE_PROFESSOR", "ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
