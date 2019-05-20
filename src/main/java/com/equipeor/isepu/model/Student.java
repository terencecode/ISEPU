package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Student extends User {

    @Column(name = "age")
    private int age;

    @Column(name = "promo")
    private String promo;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Collection<Course> courses;

    public Student(){}
    public Student(String firstName, String lastName, int age, String promo) {
        super(firstName, lastName);
        this.age = age;
        this.promo = promo;
        this.courses = courses;
    }
}
