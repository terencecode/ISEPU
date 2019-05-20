package com.equipeor.isepu.controller;



import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.exception.ProfessorIntrouvableException;
import com.equipeor.isepu.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping(value = "/Professors")
    public List<Professor> ListeProfessor(){return professorRepository.findAll();}

    @GetMapping(value = "/Professors/{id}")
    public Professor afficherProfessor(@PathVariable int id){
        Professor professeur = professorRepository.findById(id);

        if(professeur==null)throw new ProfessorIntrouvableException("Le professeur avec l'id" + id + " est introuvable.");

        return professeur;
    }

    @GetMapping(value = "/Professors/Matiere/{matiere}")
    public List<Professor> afficherProfParMatiere(@PathVariable String matiere){
        return professorRepository.findByMatiere(matiere);
    }



    @PostMapping(value = "/Professors")
    public ResponseEntity<Void> AddProfessor(@RequestBody Professor teacher){
        Professor teacherAdded  =   professorRepository.save(teacher);
        if(teacherAdded==null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teacherAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/Professors/{id}")
    public void deleteProfesseur(@PathVariable int id){
         professorRepository.deleteById(id);
    }

    @PutMapping(value = "/Professors")
    public void updateProfessor(@RequestBody Professor professor){
        professorRepository.save(professor);
    }


}
