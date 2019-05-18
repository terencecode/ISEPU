package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EleveDao extends JpaRepository<Eleve,Integer> {


    Eleve findById(int id);
    List<Eleve> findByPromo(String Promo);
}
