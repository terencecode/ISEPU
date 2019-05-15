package com.equipeor.isepu.model;

public class Professor {
    private int id;
    private String Firstname;
    private String Lastname;
    private int[] idcourses;
    private String matiere;

    public Professor(int id, String firstname, String lastname, int[] idcourses, String matiere) {
        this.id = id;
        Firstname = firstname;
        Lastname = lastname;
        this.idcourses = idcourses;
        this.matiere = matiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public int[] getIdcourses() {
        return idcourses;
    }

    public void setIdcourses(int[] idcourses) {
        this.idcourses = idcourses;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
