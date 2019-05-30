package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Professor professor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Session> sessions;

    public Course(){}
    public Course(String name, String description, Subject subject, Professor professor) {
        this.name = name;
        this.description = description;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
