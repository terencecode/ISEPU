package com.equipeor.isepu.controller;

import com.equipeor.isepu.exception.EleveIntrouvableException;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) throw new EleveIntrouvableException("L'élève avec l'id" + id + " est introuvable.");
        return student.get();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }

    @GetMapping(value = "/all/{promo}")
    public List<Student> getPromo(@PathVariable String promo) {
        return studentRepository.findByPromo(promo);
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }


}
