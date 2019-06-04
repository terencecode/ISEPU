package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.payload.response.MessageResponse;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class MessageToMessageResponseConverter extends OneWayConverter<MessageResponse, Message> {
    public MessageToMessageResponseConverter() {
        super(message -> {
            if (message == null) {
                return null;
            }

            String body = message.getBody();
            UserToUserResponseConverter converter = new UserToUserResponseConverter();
            UserResponse sender = converter.convertFromEntity(message.getSender());
            UserResponse receiver = converter.convertFromEntity(message.getReceiver());

            return new MessageResponse(body, sender, receiver);
        });
    }
}
