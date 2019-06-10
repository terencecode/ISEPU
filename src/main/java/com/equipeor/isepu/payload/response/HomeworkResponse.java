package com.equipeor.isepu.payload.response;

import com.equipeor.isepu.model.HomeworkStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HomeworkResponse {

    private HomeworkStatus status;

    private  String description;

    private SessionResponse session;

    public HomeworkResponse(HomeworkStatus status, String description) {
        this.status = status;
        this.description = description;
    }

    public HomeworkResponse(HomeworkStatus status, String description, SessionResponse session) {
        this.status = status;
        this.description = description;
        this.session = session;
    }

    public HomeworkStatus getStatus() {
        return status;
    }

    public void setStatus(HomeworkStatus status) {
        this.status = status;
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
}
