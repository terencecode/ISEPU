package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAll();
    Optional<Session> findSessionById(long id);
    Optional<List<Session>> findSessionByCourse(Course course);
}
