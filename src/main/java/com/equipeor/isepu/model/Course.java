package com.equipeor.isepu.model;


import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private int id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_description")
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Professor professor;

    @Column(name = "starting_time")
    private Instant startingTime;

    @Column(name = "finishing_time")
    private Instant finishingTime;

    public Course() {}

    public Course(String name, String description, Set<Student> students, Subject subject, Professor professor, Instant startingTime, Instant finishingTime) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.subject = subject;
        this.professor = professor;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
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
}
