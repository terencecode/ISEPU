package com.equipeor.isepu.model;


import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "starting_time")
    private Instant startingTime;

    @Column(name = "finishing_time")
    private Instant finishingTime;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Professor professor;


    public Course(){}
    public Course(String name, String description, Instant startingTime, Instant finishingTime, Subject subject, Professor professor) {
        this.name = name;
        this.description = description;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.subject = subject;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }
}
