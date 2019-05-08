package com.equipeor.isepu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;


@SpringBootApplication
public class IsepuApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsepuApplication.class, args);
	}

}

@Entity
 class Eleve{
	private  Long id;
	private String firstName;
	private String lastName;
	private String description;

	Eleve() {}

	Eleve(String firstName, String lastName, String description) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
	}

}