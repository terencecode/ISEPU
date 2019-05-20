package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findDistinctByProfessorId(int id);
}
