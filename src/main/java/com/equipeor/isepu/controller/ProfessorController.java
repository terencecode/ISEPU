package com.equipeor.isepu.controller;

import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public List<Professor> getProfessors() {
        return professorRepository.findAll();
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/subjects/{subjectName}")
    public List<Professor> getProfessorBySubject(@PathVariable String subjectName) {
        return professorRepository.findByCoursesSubjectName(subjectName);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public void deleteProfessor(@PathVariable int id) {
        professorRepository.deleteById(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public void updateProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);
    }
}
