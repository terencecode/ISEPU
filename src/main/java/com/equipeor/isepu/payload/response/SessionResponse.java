package com.equipeor.isepu.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SessionResponse {

    private Long id;

    private Instant startingTime;

    private Instant finishingTime;

    private CourseResponse course;

    private Collection<HomeworkResponse> homeworks;

    public SessionResponse(Long id, Instant startingTime, Instant finishingTime) {
        this.id = id;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
    }

    public SessionResponse(Long id, Instant startingTime, Instant finishingTime, CourseResponse course) {
        this.id = id;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.course = course;
    }

    public SessionResponse(Long id, Instant startingTime, Instant finishingTime, Collection<HomeworkResponse> homeworks) {
        this.id = id;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.homeworks = homeworks;
    }

    public SessionResponse(Long id, Instant startingTime, Instant finishingTime, CourseResponse course, Collection<HomeworkResponse> homeworks) {
        this.id = id;
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
        this.course = course;
        this.homeworks = homeworks;
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

    public CourseResponse getCourse() {
        return course;
    }

    public void setCourse(CourseResponse courseName) {
        this.course = courseName;
    }

    public Collection<HomeworkResponse> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Collection<HomeworkResponse> homeworks) {
        this.homeworks = homeworks;
    }
}
