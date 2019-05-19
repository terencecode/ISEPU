package com.equipeor.isepu.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cours {

    @Id
    @GeneratedValue
    private int id;


    private String name;
    private String Description;
    private int idstudent;
    private int idteacher;

    public Cours(int id, String name, String description, int students, int teacher) {
        this.id = id;
        this.name = name;
        Description = description;
        this.idstudent = students;
        this.idteacher = teacher;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getStudents() {
        return idstudent;
    }

    public void setStudents(int students) {
        this.idstudent = students;
    }

    public int getTeacher() {
        return idteacher;
    }

    public void setTeacher(int teacher) {
        this.idteacher = teacher;
    }
}
