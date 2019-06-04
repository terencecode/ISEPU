package com.equipeor.isepu.controller;

import com.equipeor.isepu.converter.ProfessorToUserResponseConverter;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public ResponseEntity<Collection<UserResponse>> getProfessors() {
        return ResponseEntity.ok(new ProfessorToUserResponseConverter().createFromEntities(professorRepository.findAll()));
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{email}")
    public ResponseEntity<UserResponse> getProfessor(@PathVariable String email) {
        Optional<Professor> professor = professorRepository.findByEmail(email);

        if (!professor.isPresent())
            throw new ProfessorNotFoundException("The professor with email: " + email + " not found");

        return ResponseEntity.ok(new ProfessorToUserResponseConverter().convertFromEntity(professor.get()));
    }

    /*
    @GetMapping(value = "/subjects/{subjectName}")
    public List<Professor> getProfessorBySubject(@PathVariable String subjectName) {
        return null;
    }*/

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Void> addProfessor(@RequestBody Professor teacher) {
        Professor professorAdded = professorRepository.save(teacher);
        if (professorAdded == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(professorAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{email}")
    public void deleteProfessor(@PathVariable String email) {
        professorRepository.deleteByEmail(email);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public void updateProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);
    }
}
