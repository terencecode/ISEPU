package com.equipeor.isepu.service;

import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class CourseService {


    @Autowired
    private CourseRepository courseRepository;

    public CourseService(){}

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public List<Course> afficherCourse(@PathVariable int prof){
        return courseRepository.findByProfessorId(prof);
    }





    public ResponseEntity<Void> ajouterCourse(@RequestBody Course coursname){
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

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }


    public List<Course> afficherCourseByName(String courseName) {
        return courseRepository.findByName(courseName);
    }

    public Course afficherCourseByNameAndProf(String courseName, int prof) {
        return courseRepository.findByNameAndProfessorId(courseName,prof);
    }


}
