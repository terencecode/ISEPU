package com.equipeor.isepu.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursController {
    @RequestMapping(value="/Cours", method= RequestMethod.GET)
    public String listeEleves() {
        return "Un exemple de cours";
    }
}
