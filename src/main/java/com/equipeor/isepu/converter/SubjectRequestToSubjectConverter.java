package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.request.SubjectRequest;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class SubjectRequestToSubjectConverter extends OneWayConverter<Subject, SubjectRequest> {
    public SubjectRequestToSubjectConverter() {
        super(subjectRequest -> {
            if (subjectRequest == null) {
                return null;
            }
            String name = subjectRequest.getName();
            return new Subject(name);
        });
    }
}
