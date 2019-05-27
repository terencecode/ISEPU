package com.equipeor.isepu.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class Session {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "starting_time")
    private Instant startingTime;

    @Column(name = "finishing_time")
    private Instant finishingTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @OneToMany(mappedBy = "session")
    private Set<HomeWork> homeworks;

    public Session() {}

    public Session(Instant startingTime, Instant finishingTime, Course course) {
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.course = course;
    }

    public Long getId() {
        return id;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<HomeWork> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<HomeWork> homeworks) {
        this.homeworks = homeworks;
    }
}
