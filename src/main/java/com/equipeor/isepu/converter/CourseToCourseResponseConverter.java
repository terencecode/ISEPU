package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Course;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.response.CourseResponse;
import com.equipeor.isepu.payload.response.SubjectResponse;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class CourseToCourseResponseConverter extends OneWayConverter<CourseResponse, Course> {
    public CourseToCourseResponseConverter() {
        super(course -> {
            if (course == null) {
                return null;
            }
            String name= course.getName();
            String description = course.getDescription();

            Subject subject = course.getSubject();
            SubjectToSubjectResponseConverter subjectConverter = new SubjectToSubjectResponseConverter();
            SubjectResponse subjectResponse = subjectConverter.convertFromEntity(subject);

            Professor professor = course.getProfessor();
            ProfessorToUserResponseConverter professorConverter = new ProfessorToUserResponseConverter();
            UserResponse professorResponse = professorConverter.convertFromEntity(professor);

            return new CourseResponse(name, description, subjectResponse, professorResponse);
        });
    }
}
