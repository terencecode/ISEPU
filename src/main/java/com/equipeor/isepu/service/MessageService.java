package com.equipeor.isepu.service;

import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.model.User;
import com.equipeor.isepu.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> afficherMessage() {
        return messageRepository.findAll();
    }

    public List<Message> afficherMessageBySender(User sender) {
        return messageRepository.findBySender(sender);
    }

    public List<Message> afficherMessageByReceiver(User receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    public ResponseEntity<Void> addMessage(Message message) {
        Message messageAdded = messageRepository.save(message);
        if (messageAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(messageAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
