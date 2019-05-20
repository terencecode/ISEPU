package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {


    Student findById(int id);
    List<Student> findByPromo(String Promo);
}
