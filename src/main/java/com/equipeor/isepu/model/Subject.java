package com.equipeor.isepu.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Course> courses;

    public Subject(){}

    public Subject(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id){this.id=id;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
