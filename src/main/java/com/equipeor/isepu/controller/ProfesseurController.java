package com.equipeor.isepu.controller;



import com.equipeor.isepu.dao.ProfessorDao;
import com.equipeor.isepu.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProfesseurController {

    @Autowired
    private ProfessorDao professorDao;

    @GetMapping(value = "/Professors")
    public List<Professor> ListeProfessor(){return professorDao.findAll();}
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
}
