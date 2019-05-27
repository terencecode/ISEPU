package com.equipeor.isepu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HomeWork {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
}
