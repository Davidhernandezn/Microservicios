package com.microservicios.examenes;

import javax.persistence.Entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//HABILITAR
@EnableEurekaClient //OPCIONAR YA QUE CON EL POM ESTA HABILITADO
@SpringBootApplication
//
@EntityScan({"com.commons.examenes.models.entity"})
public class MicroserviciosExamenesApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosExamenesApplication.class, args);
	}

}
