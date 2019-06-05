package com.equipeor.isepu.controller;

import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/all")
    public List<Professor> getProfessors() {
        return professorRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Professor getProfessor(@PathVariable int id) {
        Optional<Professor> professor = professorRepository.findById(id);

        if (!professor.isPresent())
            throw new ProfessorNotFoundException("Le professeur avec l'id" + id + " est introuvable.");

        return professor.get();
    }


    @GetMapping(value = "/subjects/{subjectName}")
    public Collection<Professor> getProfessorBySubject(@PathVariable String subjectName) {
        return professorRepository.findByCourses_Subject_Name(subjectName);
    }


    @PostMapping
    public ResponseEntity<Void> addProfessor(@RequestBody Professor teacher) {
        Professor professorAdded = professorRepository.save(teacher);
        if (professorAdded == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professorAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProfessor(@PathVariable int id) {
        professorRepository.deleteById(id);
    }

    @PutMapping
    public void updateProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);
    }
}
