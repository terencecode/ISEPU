package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Session;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class SessionToSessionResponseConverter extends OneWayConverter<SessionResponse, Session> {
    public SessionToSessionResponseConverter() {
        super(session -> {
            if (session == null) {
                return null;
            }
            return new SessionResponse();
        });
    }
}
