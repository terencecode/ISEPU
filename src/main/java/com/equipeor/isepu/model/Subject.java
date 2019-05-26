package com.equipeor.isepu.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject")
    private Collection<Course> courses;

    public Subject(){}

    public Subject(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
