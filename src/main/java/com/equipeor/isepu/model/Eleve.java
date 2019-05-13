package com.equipeor.isepu.model;

public class Eleve {
    private int id;
    private String prenom;
    private String nom;
    private int age;

    public Eleve(int id, String prenom, String nom, int age) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
