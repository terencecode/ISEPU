package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.payload.request.AddCourseRequest;
import com.equipeor.isepu.payload.response.CourseResponse;
import com.equipeor.isepu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<Collection<CourseResponse>> getCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{courseName}")
    public ResponseEntity<CourseResponse> getCourseByName(@PathVariable String courseName) {
        return ResponseEntity.ok(courseService.getCourseByName(courseName));
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/all/email/{professorEmail}")
    public ResponseEntity<Collection<CourseResponse>> getCoursesByProfessorEmail(@PathVariable String professorEmail) {
        return ResponseEntity.ok(courseService.getCoursesByProfessorEmail(professorEmail));
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/all/{subjectName}")
    public ResponseEntity<Collection<CourseResponse>> getCoursesBySubjectName(@PathVariable String subjectName) {
        return ResponseEntity.ok(courseService.getCoursesBySubjectName(subjectName));
    }

    @Secured("ROLE_PROFESSOR")
    @PostMapping
    public ResponseEntity<Void> addCourse(@RequestBody AddCourseRequest courseName, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.created(courseService.addCourse(courseName, userPrincipal)).build();
    }

    //TODO: addCourse via admin and professorId

    @Secured({"ROLE_PROFESSOR", "ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
