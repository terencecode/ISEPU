package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorDao extends JpaRepository<Professor,Integer> {

     Professor findById(int id);
     List<Professor> findByMatiere(String matiere);


}
