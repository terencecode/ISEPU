package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "professor_id")
    private int id;

    @Column(name = "professor_first_name")
    private String firstName;
    
    @Column(name = "professor_last_name")
    private String lastName;

    @OneToMany(mappedBy = "professor")
    private Collection<Course> courses;

    public Professor(){}

    public Professor(String firstName, String lastName, Collection<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
