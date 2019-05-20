package com.equipeor.isepu.controller;

import com.equipeor.isepu.repository.StudentRepository;
import com.equipeor.isepu.exception.EleveIntrouvableException;
import com.equipeor.isepu.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value="/Eleves")
    public List<Student> listeEleves() {
        return studentRepository.findAll();
    }
    //Récupérer un eleve par son Id
    @GetMapping(value = "/Eleves/{id}")
    public Student afficherUnEleve(@PathVariable int id) {
        Student eleve= studentRepository.findById(id);

        if(eleve==null)throw new EleveIntrouvableException("L'élève avec l'id" + id +" est introuvable.");
        return eleve;
    }

    @DeleteMapping(value = "Eleves/{id}")
    public void supprimerEleve(@PathVariable int id){


        studentRepository.deleteById(id);
    }

    @GetMapping(value = "/Eleves/{promo}")
    public List<Student> afficherPromo(@PathVariable String promo){
        return studentRepository.findByPromo(promo);
    }
    //ajouter un Eleve
    @PostMapping(value = "/Eleves")
    public ResponseEntity<Void> ajouterEleve(@RequestBody Student product) {

        Student eleveAdded =  studentRepository.save(product);

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
    public void updateEleve(@RequestBody Student eleve){
        studentRepository.save(eleve);
    }


}
