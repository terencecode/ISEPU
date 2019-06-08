package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByProfessorEmail(String email);
    Optional<Course> findByName(String courseName);
    List<Course> findBySubjectName(String subjectName);
}
