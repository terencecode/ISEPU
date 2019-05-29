package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Professor extends User {

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Course> courses;

    public Professor(){}
    public Professor(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
