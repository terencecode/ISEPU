package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeWorkRepository extends JpaRepository<HomeWork, Long> {
    List<HomeWork> findAll();
}
