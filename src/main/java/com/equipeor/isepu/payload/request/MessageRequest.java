package com.equipeor.isepu.payload.request;

public class MessageRequest {

    private String body;

    private String receiverEmail;

    public MessageRequest(String body, String receiverEmail) {
        this.body = body;
        this.receiverEmail = receiverEmail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
}
