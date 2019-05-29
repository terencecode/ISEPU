package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.*;
import com.equipeor.isepu.payload.response.CourseResponse;
import com.equipeor.isepu.payload.response.SessionResponse;
import com.equipeor.isepu.payload.response.SubjectResponse;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

import java.util.Collection;
import java.util.Set;

public class CourseToCourseResponseConverter extends OneWayConverter<CourseResponse, Course> {
    public CourseToCourseResponseConverter() {
        super(course -> {
            if (course == null) {
                return null;
            }
            String name= course.getName();
            String description = course.getDescription();

            Set<Student> students = course.getStudents();
            StudentToUserResponseConverter studentConverter = new StudentToUserResponseConverter();
            Collection<UserResponse> studentsResponse = studentConverter.createFromEntities(students);

            Subject subject = course.getSubject();
            SubjectToSubjectResponseConverter subjectConverter = new SubjectToSubjectResponseConverter();
            SubjectResponse subjectResponse = subjectConverter.convertFromEntity(subject);

            Professor professor = course.getProfessor();
            ProfessorToUserResponseConverter professorConverter = new ProfessorToUserResponseConverter();
            UserResponse professorResponse = professorConverter.convertFromEntity(professor);

            Collection<Session> sessions = course.getSessions();
            SessionToSessionResponseConverter sessionConverter = new SessionToSessionResponseConverter();
            Collection<SessionResponse> sessionResponse = sessionConverter.createFromEntities(sessions);

            return new CourseResponse(name, description, studentsResponse, subjectResponse, professorResponse, sessionResponse);
        });
    }
}
