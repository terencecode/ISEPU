package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Professor extends User {

    @OneToMany(mappedBy = "professor")
    private Collection<Course> courses;

    public Professor(){}
    public Professor(String firstName, String lastName) {
        super(firstName, lastName);
        this.courses = courses;
    }
}
