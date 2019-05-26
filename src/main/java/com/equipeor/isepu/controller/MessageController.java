package com.equipeor.isepu.controller;


import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.model.User;
import com.equipeor.isepu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getMessages() {
        return messageService.afficherMessage();
    }

    @GetMapping(value = "/{sender}")
    public List<Message> getMessageBySender(@PathVariable User sender) {
        return messageService.getMessageBySender(sender);
    }

    @GetMapping(value = "/{receiver}")
    public List<Message> getMessageByReceiver(@PathVariable User receiver) {
        return messageService.getMessageByReceiver(receiver);
    }

    @PostMapping
    public ResponseEntity<Void> addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }


}
