package com.equipeor.isepu.payload.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SessionRequest {

    private Long id;

    private Long startingTime;

    private Long finishingTime;

    private String courseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Long startingTime) {
        this.startingTime = startingTime;
    }

    public Long getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(Long finishingTime) {
        this.finishingTime = finishingTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
