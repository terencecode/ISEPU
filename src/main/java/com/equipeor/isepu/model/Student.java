package com.equipeor.isepu.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Student extends User {

    @Column(name = "promo")
    private String promo;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Collection<Course> courses;


    public Student(){}
    public Student(String firstName, String lastName, String email, String password, String promo) {
        super(firstName, lastName, email, password);
        this.promo = promo;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    @Override
    public Collection<Course> getCourses() {
        return courses;
    }

    @Override
    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
