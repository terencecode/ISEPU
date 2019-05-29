package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.response.SubjectResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

import java.util.Collection;

public class SubjectToSubjectResponseConverter extends OneWayConverter<SubjectResponse, Subject> {
    public SubjectToSubjectResponseConverter() {
        super(subject -> {
            if (subject == null) {
                return null;
            }
            String name = subject.getName();
            Collection<Course> courses = subject.getCourses();
            return new SubjectResponse(name);
        });
    }
}
