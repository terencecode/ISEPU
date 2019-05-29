package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.AddCourseRequest;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class CourseRequestToCourseConverter extends OneWayConverter<Course, AddCourseRequest> {
    public CourseRequestToCourseConverter(Subject courseSubject, Professor professor) {
        super(addCourseRequest -> {
            if (addCourseRequest == null) {
                return null;
            }
            String name = addCourseRequest.getName();
            String description = addCourseRequest.getDescription();
            return new Course(name, description, courseSubject, professor);
        });
    }
}
