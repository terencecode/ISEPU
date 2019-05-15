package com.equipeor.isepu.dao;

import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDaoImpl implements ProfessorDao {
    public static List<Professor> professors = new ArrayList<>();
    static {
        professors.add(new Professor(1,"William", "Trojanowski", new int[]{21,22},"physique"));
        professors.add(new Professor(2,"Amele", "Nouna", new int[]{23},"math"));
        professors.add(new Professor(3,"Clément", "Phu", new int[]{24},"jsp"));
    }

    @Override
    public List<Professor> findAll() {
        return professors;
    }

    @Override
    public Professor ProfessorFindById(int id) {
        for (Professor prof : professors) {
            if(prof.getId() ==id){
                return prof;
            }
        }
        return null;
    }

    @Override
    public Professor save(Professor teacher) {
        professors.add(teacher);
        return teacher;
    }


}
