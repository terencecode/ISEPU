package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAll();
}
