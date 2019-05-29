package com.equipeor.isepu.controller;

import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping(value = "/all/{promo}")
    public List<Student> getPromo(@PathVariable String promo) {
        return studentRepository.findByPromo(promo);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id) {
        studentRepository.save(student);
    }
}
