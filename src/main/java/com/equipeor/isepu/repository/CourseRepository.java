package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByProfessorId(int id);
    List<Course> findByName(String courseName);
    Course findByNameAndProfessorId(String courseName, int prof);
}
