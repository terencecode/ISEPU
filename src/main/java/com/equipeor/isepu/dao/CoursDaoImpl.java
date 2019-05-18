package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Cours;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Eleve;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CoursDaoImpl implements CoursDao {
    public static List<Cours> coursList= new ArrayList<>();
    static{
        coursList.add(new Cours(1,"Math","Cours de maths",new Eleve[]{},new Professor(1,"Clement","Phu","Math")));
    }


    @Override
    public List<Cours> findAll() {
        return coursList;
    }

    @Override
    public Cours coursFindById(int id) {
        for (Cours cours : coursList) {
            if(cours.getId() ==id){
                return cours;
            }
        }
        return null;
    }

    @Override
    public Cours save(Cours math) {
        coursList.add(math);
        return math;
    }
}
