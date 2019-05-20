package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {

     Professor findById(int id);
     List<Professor> findByMatiere(String matiere);


}
