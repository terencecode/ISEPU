package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Professor extends User {

    @OneToMany(mappedBy = "professor")
    private Collection<Course> courses;

    public Professor(String firstName, String lastName) {
        super(firstName, lastName);
        this.courses = courses;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
