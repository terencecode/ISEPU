package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findById(long id);
    List<Student> findByPromo(String Promo);
    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);
}
