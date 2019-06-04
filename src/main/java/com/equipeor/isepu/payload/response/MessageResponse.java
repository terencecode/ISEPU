package com.equipeor.isepu.payload.response;

public class MessageResponse {

    private String body;

    private UserResponse sender;

    private UserResponse receiver;

    public MessageResponse(String body, UserResponse sender, UserResponse receiver) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UserResponse getSender() {
        return sender;
    }

    public void setSender(UserResponse sender) {
        this.sender = sender;
    }

    public UserResponse getReceiver() {
        return receiver;
    }

    public void setReceiver(UserResponse receiver) {
        this.receiver = receiver;
    }
}
