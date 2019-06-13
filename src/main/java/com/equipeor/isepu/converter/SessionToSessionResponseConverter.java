package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Session;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class SessionToSessionResponseConverter extends OneWayConverter<SessionResponse, Session> {
    public SessionToSessionResponseConverter(boolean includeHomeworks, boolean includeCourse) {
        super(session -> {
            if (session == null) {
                return null;
            }
            SessionResponse response = new SessionResponse(session.getId(), session.getStartingTime(), session.getFinishingTime());
            if (includeHomeworks) {
                response.setHomeworks(new HomeworkToHomeworkResponseConverter(false).createFromEntities(session.getHomeworks()));
            }
            if (includeCourse) {
                response.setCourse(new CourseToCourseResponseConverter(false).convertFromEntity(session.getCourse()));
            }
            return response;
        });
    }
}
