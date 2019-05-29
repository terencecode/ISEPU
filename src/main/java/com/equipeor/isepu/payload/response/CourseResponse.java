package com.equipeor.isepu.payload.response;

import java.util.Collection;

public class CourseResponse {

    private String name;

    private String description;

    private Collection<UserResponse> students;

    private SubjectResponse subject;

    private UserResponse professor;

    private Collection<SessionResponse> sessions;

    public CourseResponse(String name, String description, Collection<UserResponse> students, SubjectResponse subject, UserResponse professor, Collection<SessionResponse> sessions) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.subject = subject;
        this.professor = professor;
        this.sessions = sessions;
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

    public Collection<UserResponse> getStudents() {
        return students;
    }

    public void setStudents(Collection<UserResponse> students) {
        this.students = students;
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

    public Collection<SessionResponse> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<SessionResponse> sessions) {
        this.sessions = sessions;
    }
}
