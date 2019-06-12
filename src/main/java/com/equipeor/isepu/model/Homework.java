package com.equipeor.isepu.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Homework {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 5000)
    private  String description;

    @ManyToOne
    @JoinColumn
    private Session session;

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HomeworkState> HomeworkStates;

    public Homework() {}

    public Homework(String description, Session session) {
        this.description = description;
        this.session = session;
    }

    public Homework(String description, Session session, Set<HomeworkState> homeworkStates) {
        this.description = description;
        this.session = session;
        HomeworkStates = homeworkStates;
    }

    public Homework(Session session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HomeworkState> getHomeworkStates() {
        return HomeworkStates;
    }

    public void setHomeworkStates(Set<HomeworkState> homeworkStates) {
        HomeworkStates = homeworkStates;
    }
}
