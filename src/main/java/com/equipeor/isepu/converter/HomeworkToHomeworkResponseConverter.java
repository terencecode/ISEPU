package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Homework;
import com.equipeor.isepu.payload.response.HomeworkResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class HomeworkToHomeworkResponseConverter extends OneWayConverter<HomeworkResponse, Homework> {
    public HomeworkToHomeworkResponseConverter(boolean includeSession) {
        super(homework -> {
            if (homework == null) {
                return null;
            }
            return includeSession ?  new HomeworkResponse(homework.getId(), homework.getDescription(),
                    new SessionToSessionResponseConverter(false, true).convertFromEntity(homework.getSession())) :
                    new HomeworkResponse(homework.getId(), homework.getDescription());
        });
    }
}
