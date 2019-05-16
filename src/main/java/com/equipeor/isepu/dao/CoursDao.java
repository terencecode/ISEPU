package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Cours;

import java.util.List;

public interface CoursDao {
    public List<Cours> findAll();
    public Cours coursFindById(int id);
    public Cours save(Cours math);
}
