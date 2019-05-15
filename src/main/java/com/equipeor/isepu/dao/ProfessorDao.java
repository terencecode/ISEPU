package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Professor;

import java.util.List;

public interface ProfessorDao {
    public List<Professor>findAll();
    public Professor ProfessorFindById(int id);
    public Professor save(Professor teacher);
}
