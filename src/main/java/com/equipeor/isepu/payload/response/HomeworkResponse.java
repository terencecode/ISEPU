package com.equipeor.isepu.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HomeworkResponse {

    private Long id;

    private  String description;

    private String status;

    private SessionResponse session;

    public HomeworkResponse(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public HomeworkResponse(Long id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public HomeworkResponse(Long id, String description, SessionResponse session) {
        this.id = id;
        this.description = description;
        this.session = session;
    }

    public HomeworkResponse(Long id, String description, String status, SessionResponse session) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SessionResponse getSession() {
        return session;
    }

    public void setSession(SessionResponse session) {
        this.session = session;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
