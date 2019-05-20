package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    Subject findById(int id);
    List<Subject> findAll();

    Subject findByName(String name);

    void deleteByName(String name);
}
