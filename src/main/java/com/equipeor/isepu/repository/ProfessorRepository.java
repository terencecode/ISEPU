package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
     Optional<Professor> findById(long id);
     Collection<Professor> findByCourses_Subject_Name(String name);
     boolean existsByEmail(String email);
     Optional<Professor> findByEmail(String email);
     void deleteByEmail(String email);
}
