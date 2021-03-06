package com.equipeor.isepu.repository;

import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllBySenderOrReceiver(User sender, User receiver);

    List<Message> findBySender(User sender);

    List<Message> findByReceiver(User receiver);
}
