package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findById(Long id);
    Set<Student> findAllByEmailIn(Collection<String> emails);
    List<Student> findByPromo(String Promo);
    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);
    void deleteByEmail(String email);
}
