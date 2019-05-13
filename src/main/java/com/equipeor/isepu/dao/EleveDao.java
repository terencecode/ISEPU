package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Eleve;

import java.util.List;

public interface EleveDao {
    public List<Eleve> findAll();
    public Eleve ElevefindById(int id);
    public Eleve save(Eleve product);
}
