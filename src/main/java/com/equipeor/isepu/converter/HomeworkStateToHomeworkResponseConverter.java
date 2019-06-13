package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.HomeworkState;
import com.equipeor.isepu.payload.response.HomeworkResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class HomeworkStateToHomeworkResponseConverter extends OneWayConverter<HomeworkResponse, HomeworkState> {
    public HomeworkStateToHomeworkResponseConverter(boolean includeSession) {
        super(homeworkState -> {
            if (homeworkState == null) {
                return null;
            }
            return includeSession ? new HomeworkResponse(homeworkState.getHomework().getId(), homeworkState.getHomework().getDescription(), homeworkState.getStatus().toString(), new SessionToSessionResponseConverter(false, true).convertFromEntity(homeworkState.getHomework().getSession()))
                    : new HomeworkResponse(homeworkState.getHomework().getId(), homeworkState.getHomework().getDescription(), homeworkState.getStatus().toString());
        });
    }
}
