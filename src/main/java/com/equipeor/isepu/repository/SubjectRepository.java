package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findById(Long id);
    List<Subject> findAll();
    Optional<Subject> findByName(String name);
    void deleteByName(String name);
    void deleteById(Long id);
    Collection<Subject> findAllByCourses_Professor_Email(String email);
    Collection<Subject> findAllByCourses_Students_Email(String email);
}
