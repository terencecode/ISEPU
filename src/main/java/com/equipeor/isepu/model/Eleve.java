package com.equipeor.isepu.model;



import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Eleve {

    @Id
    @GeneratedValue
    private int id;

    private String prenom;
    private String nom;
    private int age;
    private String promo;


public Eleve(){}

    public Eleve(int id, String prenom, String nom, int age, String promo) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.promo=promo;
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

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
