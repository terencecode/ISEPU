package com.equipeor.isepu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EleveController {
    @RequestMapping(value="/Produits", method= RequestMethod.GET)
    public String listeEleves() {
        return "Un exemple d'élève";
    }
}
