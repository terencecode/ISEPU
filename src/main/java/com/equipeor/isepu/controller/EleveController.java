package com.equipeor.isepu.controller;

import com.equipeor.isepu.dao.EleveDao;
import com.equipeor.isepu.exception.EleveIntrouvableException;
import com.equipeor.isepu.model.Eleve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    //Récupérer un eleve par son Id
    @GetMapping(value = "/Eleves/{id}")
    public Eleve afficherUnEleve(@PathVariable int id) {
        Eleve eleve=eleveDao.findById(id);

        if(eleve==null)throw new EleveIntrouvableException("L'élève avec l'id" + id +" est introuvable.");
        return eleve;
    }

    @DeleteMapping(value = "Eleves/{id}")
    public void supprimerEleve(@PathVariable int id){
        eleveDao.deleteById(id);
    }

    @GetMapping(value = "/Eleves/{promo}")
    public List<Eleve> afficherPromo(@PathVariable String promo){
        return eleveDao.findByPromo(promo);
    }
    //ajouter un Eleve
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

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/Eleves")
    public void updateEleve(@RequestBody Eleve eleve){
        eleveDao.save(eleve);
    }


}
