package com.equipeor.isepu.model;

public class Cours {


    private int id;
    private String name;
    private String Description;
    private Eleve[] students;
    private Professor teacher;

    public Cours(int id, String name, String description, Eleve[] students, Professor teacher) {
        this.id = id;
        this.name = name;
        Description = description;
        this.students = students;
        this.teacher = teacher;
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

    public Eleve[] getStudents() {
        return students;
    }

    public void setStudents(Eleve[] students) {
        this.students = students;
    }

    public Professor getTeacher() {
        return teacher;
    }

    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
    }
}
