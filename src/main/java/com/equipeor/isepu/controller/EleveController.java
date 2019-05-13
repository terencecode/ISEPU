package com.equipeor.isepu.controller;

import com.equipeor.isepu.dao.EleveDao;
import com.equipeor.isepu.model.Eleve;
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
public class EleveController {

    @Autowired
    private EleveDao eleveDao;

    @GetMapping(value="/Eleves")
    public List<Eleve> listeEleves() {
        return eleveDao.findAll();
    }

    //ajouter un produit
    @PostMapping(value = "/Eleves")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Eleve product) {

        Eleve eleveAdded =  eleveDao.save(product);

        if (eleveAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eleveAdded.getId())
                .toUri();

        return ResponseEntity.ok().build();
    }


}
