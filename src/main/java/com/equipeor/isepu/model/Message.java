package com.equipeor.isepu.model;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "body", length = 5000)
    private String body;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User receiver;

    public Message(){}

    public Message(String body, User sender, User receiver) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getId() {
        return this.id;
    }
}
