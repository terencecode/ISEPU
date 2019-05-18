package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorDao extends JpaRepository<Professor,Integer> {

     Professor FindById(int id);
     List<Professor> FindByMatiere(String matiere);
     Professor FindByIdcourses(int id);


}
