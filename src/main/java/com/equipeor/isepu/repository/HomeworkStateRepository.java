package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.HomeworkState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkStateRepository extends JpaRepository<HomeworkState, Long> {
    List<HomeworkState> findAll();
    List<HomeworkState> findAllByStudentId(Long id);
}
