package com.equipeor.isepu.controller;

import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Secured({"ROLE_ADMIN", "ROLE_PROFESSOR"})
    @GetMapping("/all")
    public ResponseEntity<Collection<UserResponse>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String email) {
        studentService.deleteStudent(email);
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping(value = "/all/{promo}")
    public ResponseEntity<Collection<UserResponse>> getPromo(@PathVariable String promo) {
        return ResponseEntity.ok(studentService.getPromo(promo));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id) {
        studentService.updateStudent(student,id);
    }
}
