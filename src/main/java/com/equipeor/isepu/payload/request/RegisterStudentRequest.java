package com.equipeor.isepu.payload.request;

import java.util.Collection;

public class RegisterStudentRequest {

    private String courseName;
    private Collection<String> studentEmails;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Collection<String> getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(Collection<String> studentEmails) {
        this.studentEmails = studentEmails;
    }
}
