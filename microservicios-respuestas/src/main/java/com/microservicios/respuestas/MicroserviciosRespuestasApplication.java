package com.microservicios.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//HABILITAR
@EnableEurekaClient //OPCIONAR YA QUE CON EL POM ESTA HABILITADO
@SpringBootApplication

//Configurarlo para reconocer los commons alumnos y examenes
@EntityScan({"com.microservicios.respuestas.models.entity",
			 "com.microservicios.commons.alumnos.models.entity",
			 "com.commons.examenes.models.entity"})

public class MicroserviciosRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosRespuestasApplication.class, args);
	}

}
