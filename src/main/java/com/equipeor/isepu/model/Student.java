package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Student extends User {

    @Column(name = "promo")
    private String promo;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Collection<Course> courses;


    public Student(){}
    public Student(String firstName, String lastName, String email, String password, String promo) {
        super(firstName, lastName, email, password);
        this.promo = promo;
    }
}
