package com.equipeor.isepu.model;

import javax.persistence.*;

@Entity
public class HomeWork {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private HomeWorkStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Session session;

    public HomeWork() {}

    public HomeWork(Session session) {
        this.session = session;
        this.status = HomeWorkStatus.TO_DO;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public HomeWorkStatus getStatus() {
        return status;
    }

    public void setStatus(HomeWorkStatus status) {
        this.status = status;
    }
}
