package com.equipeor.isepu.controller;

import com.equipeor.isepu.dao.EleveDao;
import com.equipeor.isepu.model.Eleve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EleveController {

    @Autowired
    private EleveDao eleveDao;

    @RequestMapping(value="/Eleves", method= RequestMethod.GET)
    public List<Eleve> listeEleves() {
        return eleveDao.findAll();
    }
}
