package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
<<<<<<< Updated upstream
    Subject findById(long id);
=======
    Subject findById(Long id);
>>>>>>> Stashed changes
    List<Subject> findAll();
    Optional<Subject> findByName(String name);
    void deleteByName(String name);
    void deleteById(Long id);
}
