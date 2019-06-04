package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.User;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class UserToUserResponseConverter extends OneWayConverter<UserResponse, User> {
    public UserToUserResponseConverter() {
        super(user -> {
            if (user == null) {
                return null;
            }

            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();

            return new UserResponse(firstName, lastName, email);
        });
    }
}
