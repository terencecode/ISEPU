package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Student extends Person{

    @Column(name = "age")
    private int age;

    @Column(name = "promo")
    private String promo;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> courses;

    public Student(String firstName, String lastName, int age, String promo) {
        super(firstName, lastName);
        this.age = age;
        this.promo = promo;
        this.courses = courses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
