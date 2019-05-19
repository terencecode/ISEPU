package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursDao extends JpaRepository<Cours,Integer> {

    Cours findDistinctByIdteacher(int id);

}
