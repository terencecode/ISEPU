package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Session;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class SessionToSessionResponseConverter extends OneWayConverter<SessionResponse, Session> {
    public SessionToSessionResponseConverter(boolean includeHomeworks) {
        super(session -> {
            if (session == null) {
                return null;
            }
            return includeHomeworks ? new SessionResponse(session.getId(), session.getStartingTime(), session.getFinishingTime(), session.getCourse().getName(), new HomeworkToHomeworkResponseConverter(false) .createFromEntities(session.getHomework())) :
                    new SessionResponse(session.getId(), session.getStartingTime(), session.getFinishingTime(), session.getCourse().getName());
        });
    }
}
