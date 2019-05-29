package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.ProfessorToUserResponseConverter;
import com.equipeor.isepu.converter.StudentToUserResponseConverter;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.model.User;
import com.equipeor.isepu.payload.request.UserRequest;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public UserResponse getCurrentUser(UserPrincipal userPrincipal) {

        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());

        if (student.isPresent()) {
            return new StudentToUserResponseConverter().convertFromEntity(student.get());
        } else if (professor.isPresent()) {
            return new ProfessorToUserResponseConverter().convertFromEntity(professor.get());
        } else throw new UserNotFoundException();
    }

    public void updateCurrentUser(UserRequest userRequest, UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());

        if (student.isPresent()) {
            Student updatedStudent = student.get();
            updateCurrentUser(userRequest, updatedStudent);
            updatedStudent.setPromo(userRequest.getPromo() == null ? updatedStudent.getPromo() : userRequest.getPromo());
            studentRepository.save(updatedStudent);
        } else if (professor.isPresent()) {
            Professor updatedProfessor = professor.get();
            updateCurrentUser(userRequest, updatedProfessor);
            professorRepository.save(updatedProfessor);
        } else throw new UserNotFoundException();
    }

    private void updateCurrentUser(UserRequest userRequest, User user) {
        user.setFirstName(userRequest.getFirstName() == null ? user.getFirstName() : userRequest.getFirstName());
        user.setLastName(userRequest.getLastName() == null ? user.getLastName() : userRequest.getLastName());
        user.setEmail(userRequest.getEmail() == null ? user.getEmail() : userRequest.getEmail());
        user.setPassword(userRequest.getPassword() == null ? user.getPassword() : userRequest.getPassword());
    }
}
