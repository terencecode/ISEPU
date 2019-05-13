package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Eleve;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EleveDaoImpl implements EleveDao {

    public static List<Eleve> eleves = new ArrayList<>();
    static {
        eleves.add(new Eleve(1,"William", "Trojanowski", 21));
        eleves.add(new Eleve(2,"Amele", "Nouna", 21));
        eleves.add(new Eleve(3,"Clément", "Phu", 22));
    }

    @Override
    public List<Eleve> findAll() {
        return eleves;
    }

    @Override
    public Eleve ElevefindById(int id) {
        for (Eleve eleve : eleves) {
            if(eleve.getId() ==id){
                return eleve;
            }
        }
        return null;
    }

    @Override
    public Eleve save(Eleve eleve) {
        eleves.add(eleve);
        return eleve;
    }
}
