package com.equipeor.isepu.service;

import com.equipeor.isepu.converter.StudentToUserResponseConverter;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Collection<UserResponse> getStudents() {
        return new StudentToUserResponseConverter().createFromEntities(studentRepository.findAll());
    }

    public void deleteStudent(String email) {
        studentRepository.deleteByEmail(email);
    }

    public Collection<UserResponse> getPromo(String promo) {
        return new StudentToUserResponseConverter().createFromEntities(studentRepository.findByPromo(promo));
    }

    public void updateStudent(Student student, int id) {
        studentRepository.save(student);
    }
}
