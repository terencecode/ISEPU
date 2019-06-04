package com.equipeor.isepu.converter;

import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.model.User;
import com.equipeor.isepu.payload.request.MessageRequest;
import com.equipeor.isepu.utils.converter.OneWayConverter;

public class MessageRequestToMessageConverter extends OneWayConverter<Message, MessageRequest> {
    public MessageRequestToMessageConverter(User sender, User receiver) {
        super(messageRequest -> {
            if (messageRequest == null) {
                return null;
            }
            return new Message(messageRequest.getBody(), sender, receiver);
        });
    }
}
