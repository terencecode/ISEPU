package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.payload.request.MessageRequest;
import com.equipeor.isepu.payload.response.MessageResponse;
import com.equipeor.isepu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public ResponseEntity<Collection<MessageResponse>> getMessages(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(messageService.getMessages(userPrincipal));
    }

    @Secured("ROLE_USER")
    @GetMapping("/sent")
    public ResponseEntity<Collection<MessageResponse>> getMessagesSent(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(messageService.getMessagesSent(userPrincipal));
    }

    @Secured("ROLE_USER")
    @GetMapping("/received")
    public ResponseEntity<Collection<MessageResponse>> getMessagesReceived(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(messageService.getMessagesReceived(userPrincipal));
    }

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest messageRequest, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.created(messageService.sendMessage(messageRequest, userPrincipal)).build();
    }


}
