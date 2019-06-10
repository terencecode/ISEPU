package com.equipeor.isepu.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SessionResponse {

    private Instant startingTime;

    private Instant finishingTime;

    private String courseName;

    private Collection<HomeworkResponse> homework;

    public SessionResponse(Instant startingTime, Instant finishingTime, String courseName) {
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.courseName = courseName;
    }

    public SessionResponse(Instant startingTime, Instant finishingTime, String courseName, Collection<HomeworkResponse> homework) {
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.courseName = courseName;
        this.homework = homework;
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

    public Collection<HomeworkResponse> getHomework() {
        return homework;
    }

    public void setHomework(Collection<HomeworkResponse> homework) {
        this.homework = homework;
    }
}
