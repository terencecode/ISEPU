package com.equipeor.isepu.model;


import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Instant startingTime) {
        this.startingTime = startingTime;
    }

    public Instant getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(Instant finishingTime) {
        this.finishingTime = finishingTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
