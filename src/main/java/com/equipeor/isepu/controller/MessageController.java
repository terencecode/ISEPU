package com.equipeor.isepu.controller;


import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.model.User;
import com.equipeor.isepu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/Message")
    public List<Message> afficherMessages(){
        return messageService.afficherMessage();
    }

    @GetMapping(value = "/Message/{sender}")
    public List<Message> afficherMessageBySender(@PathVariable User sender){
        return messageService.afficherMessageBySender(sender);
    }

    @GetMapping(value = "/Message/{receiver}")
    public List<Message> afficherMessageByReceiver(@PathVariable User receiver){
        return messageService.afficherMessageByReceiver(receiver);
    }

    @PostMapping(value = "/Message")
    public ResponseEntity<Void> addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }




}
