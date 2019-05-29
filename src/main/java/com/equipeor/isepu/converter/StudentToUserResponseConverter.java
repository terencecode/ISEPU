package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class StudentToUserResponseConverter extends OneWayConverter<UserResponse, Student> {
    public StudentToUserResponseConverter() {
        super(student -> {
            if (student == null) {
                return null;
            }

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String email = student.getEmail();
            String promo = student.getPromo();

            return new UserResponse(firstName, lastName, email, promo);
        });
    }
}
