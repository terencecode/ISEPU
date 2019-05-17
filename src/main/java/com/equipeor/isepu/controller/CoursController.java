package com.equipeor.isepu.controller;


import com.equipeor.isepu.dao.CoursDao;
import com.equipeor.isepu.model.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CoursController {

    @Autowired
    private CoursDao coursDao;


    @GetMapping(value="/Cours")
    public List<Cours> listCours() {
        return coursDao.findAll();
    }

    //ajouter un cours
    @PostMapping(value="/Cours")
    public ResponseEntity<Void> ajouterCours(@RequestBody Cours coursname){
        Cours coursAdded = coursDao.save(coursname);
        if (coursAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(coursAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
