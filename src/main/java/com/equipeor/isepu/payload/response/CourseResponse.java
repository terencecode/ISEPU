package com.equipeor.isepu.payload.response;

public class CourseResponse {

    private String name;

    private String description;

    private SubjectResponse subject;

    private UserResponse professor;

    public CourseResponse(String name, String description, SubjectResponse subject, UserResponse professor) {
        this.name = name;
        this.description = description;
        this.subject = subject;
        this.professor = professor;
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

    public SubjectResponse getSubject() {
        return subject;
    }

    public void setSubject(SubjectResponse subject) {
        this.subject = subject;
    }

    public UserResponse getProfessor() {
        return professor;
    }

    public void setProfessor(UserResponse professor) {
        this.professor = professor;
    }
}
