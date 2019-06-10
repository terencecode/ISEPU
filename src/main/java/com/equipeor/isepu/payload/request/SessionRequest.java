package com.equipeor.isepu.payload.request;

import java.time.Instant;

public class SessionRequest {

    private Instant startingTime;

    private Long finishingTime;

    private Long courseName;

    public Instant getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Instant startingTime) {
        this.startingTime = startingTime;
    }

    public Long getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(Long finishingTime) {
        this.finishingTime = finishingTime;
    }

    public Long getCourseName() {
        return courseName;
    }

    public void setCourseName(Long courseName) {
        this.courseName = courseName;
    }
}
