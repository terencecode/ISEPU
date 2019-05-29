package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.payload.UserResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class ProfessorToUserResponseConverter extends OneWayConverter<UserResponse, Professor> {
    public ProfessorToUserResponseConverter() {
        super(professor -> {
            if (professor == null) {
                return null;
            }

            String firstName = professor.getFirstName();
            String lastName = professor.getLastName();
            String email = professor.getEmail();

            return new UserResponse(firstName, lastName, email);
        });
    }
}
