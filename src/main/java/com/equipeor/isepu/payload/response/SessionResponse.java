package com.equipeor.isepu.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SessionResponse {

    private Long id;

    private Instant startingTime;

    private Instant finishingTime;

    private String courseName;

    private Collection<HomeworkResponse> homework;

    public SessionResponse(Long id, Instant startingTime, Instant finishingTime, String courseName) {
        this.id = id;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.courseName = courseName;
    }

    public SessionResponse(Long id, Instant startingTime, Instant finishingTime, String courseName, Collection<HomeworkResponse> homework) {
        this.id = id;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.courseName = courseName;
        this.homework = homework;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Collection<HomeworkResponse> getHomework() {
        return homework;
    }

    public void setHomework(Collection<HomeworkResponse> homework) {
        this.homework = homework;
    }
}
