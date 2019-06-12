package com.equipeor.isepu.model;

import javax.persistence.*;

@Entity
public class HomeworkState {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private HomeworkStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Homework homework;

    @ManyToOne
    @JoinColumn(nullable = false)
    private  Student student;

    public HomeworkState(HomeworkStatus status, Homework homework, Student student) {
        this.status = status;
        this.homework = homework;
        this.student = student;
    }

    public HomeworkStatus getStatus() {
        return status;
    }

    public void setStatus(HomeworkStatus status) {
        this.status = status;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
