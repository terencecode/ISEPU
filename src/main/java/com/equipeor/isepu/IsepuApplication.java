package com.equipeor.isepu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EntityScan

@EnableJpaRepositories("com.equipeor.isepu.repository")
@EnableSwagger2
public class IsepuApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsepuApplication.class, args);
	}
}