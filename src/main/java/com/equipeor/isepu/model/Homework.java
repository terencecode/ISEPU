package com.equipeor.isepu.model;

import javax.persistence.*;

@Entity
public class Homework {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private HomeworkStatus status;

    @ManyToOne
    @JoinColumn
    private Session session;

    public Homework() {}

    public Homework(Session session) {
        this.session = session;
        this.status = HomeworkStatus.TO_DO;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public HomeworkStatus getStatus() {
        return status;
    }

    public void setStatus(HomeworkStatus status) {
        this.status = status;
    }
}
