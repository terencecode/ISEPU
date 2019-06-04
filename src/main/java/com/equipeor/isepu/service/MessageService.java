package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.MessageRequestToMessageConverter;
import com.equipeor.isepu.converter.MessageToMessageResponseConverter;
import com.equipeor.isepu.exception.MessageNotSentException;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Message;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.model.User;
import com.equipeor.isepu.payload.request.MessageRequest;
import com.equipeor.isepu.payload.response.MessageResponse;
import com.equipeor.isepu.repository.MessageRepository;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.StudentRepository;
import com.equipeor.isepu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    public Collection<MessageResponse> getMessages(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());

        if (student.isPresent()) {
            return new MessageToMessageResponseConverter().createFromEntities(messageRepository.findAllBySenderOrReceiver(student.get(), student.get()));
        } else if (professor.isPresent()) {
            return new MessageToMessageResponseConverter().createFromEntities(messageRepository.findAllBySenderOrReceiver(professor.get(), professor.get()));
        } else throw new UserNotFoundException();
    }

    public Collection<MessageResponse> getMessagesSent(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());

        if (student.isPresent()) {
            return new MessageToMessageResponseConverter().createFromEntities(messageRepository.findBySender(student.get()));
        } else if (professor.isPresent()) {
            return new MessageToMessageResponseConverter().createFromEntities(messageRepository.findBySender(professor.get()));
        } else throw new UserNotFoundException();
    }

    public Collection<MessageResponse> getMessagesReceived(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());

        if (student.isPresent()) {
            return new MessageToMessageResponseConverter().createFromEntities(messageRepository.findByReceiver(student.get()));
        } else if (professor.isPresent()) {
            return new MessageToMessageResponseConverter().createFromEntities(messageRepository.findByReceiver(professor.get()));
        } else throw new UserNotFoundException();
    }

    public URI sendMessage(MessageRequest messageRequest, UserPrincipal userPrincipal) {

        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());
        Optional<User> receiver = userRepository.findByEmail(messageRequest.getReceiverEmail());
        Message message;

        if (student.isPresent() && receiver.isPresent()) {
            MessageRequestToMessageConverter converter = new MessageRequestToMessageConverter(student.get(), receiver.get());
            message = converter.convertFromEntity(messageRequest);
        } else if (professor.isPresent() && receiver.isPresent()) {
            MessageRequestToMessageConverter converter = new MessageRequestToMessageConverter(professor.get(), receiver.get());
            message = converter.convertFromEntity(messageRequest);
        } else throw new UserNotFoundException();

        Message messageAdded = messageRepository.save(message);
        if (messageAdded == null)
            throw new MessageNotSentException();

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(messageAdded.getId())
                .toUri();
    }
}
