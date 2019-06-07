package com.equipeor.isepu.service;

import com.equipeor.isepu.converter.ProfessorToUserResponseConverter;
import com.equipeor.isepu.exception.ProfessorNotFoundException;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.payload.response.UserResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Collection<UserResponse> getProfessors() {
        return new ProfessorToUserResponseConverter().createFromEntities(professorRepository.findAll());
    }

    public UserResponse getProfessor(@PathVariable String email) {
        Optional<Professor> professor = professorRepository.findByEmail(email);

        if (!professor.isPresent())
            throw new ProfessorNotFoundException("The professor with email: " + email + " not found");

        return new ProfessorToUserResponseConverter().convertFromEntity(professor.get());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public void addProfessor(@RequestBody Professor teacher, URI location) {
        Professor professorAdded = professorRepository.save(teacher);
        if (professorAdded == null) {
            return;
        }
        location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(professorAdded.getId())
                .toUri();
    }

    public void deleteProfessor(String email) {
        professorRepository.deleteByEmail(email);
    }

    public void updateProfessor(Professor professor) {
        professorRepository.save(professor);
    }
}
