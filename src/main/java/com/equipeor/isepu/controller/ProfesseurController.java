package com.equipeor.isepu.controller;



import com.equipeor.isepu.dao.ProfessorDao;
import com.equipeor.isepu.exception.ProfessorIntrouvableException;
import com.equipeor.isepu.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProfesseurController {

    @Autowired
    private ProfessorDao professorDao;

    @GetMapping(value = "/Professors")
    public List<Professor> ListeProfessor(){return professorDao.findAll();}

    @GetMapping(value = "/Professors/{id}")
    public Professor afficherProfessor(@PathVariable int id){
        Professor professeur =professorDao.FindById(id);

        if(professeur==null)throw new ProfessorIntrouvableException("Le professeur avec l'id" + id + " est introuvable.");

        return professeur;
    }

    @GetMapping(value = "/Professors/Matiere/{matiere}")
    public List<Professor> afficherProfParPromo(@PathVariable String matiere){
        return professorDao.FindByMatiere(matiere);
    }

    @GetMapping(value = "/Professors/Cours/{id}")
    public Professor afficherProfParCours(@PathVariable int idcours){
        return professorDao.FindByIdcourses(idcours);
    }

    @PostMapping(value = "/Professors")
    public ResponseEntity<Void> AddProfessor(@RequestBody Professor teacher){
        Professor teacherAdded  =   professorDao.save(teacher);
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
         professorDao.deleteById(id);
    }

    @PutMapping(value = "/Professors")
    public void updateProfessor(@RequestBody Professor professor){
        professorDao.save(professor);
    }


}
