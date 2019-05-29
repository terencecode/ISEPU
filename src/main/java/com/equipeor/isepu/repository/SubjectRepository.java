package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    Subject findById(long id);
    List<Subject> findAll();

    Optional<Subject> findByName(String name);

    void deleteByName(String name);
}
