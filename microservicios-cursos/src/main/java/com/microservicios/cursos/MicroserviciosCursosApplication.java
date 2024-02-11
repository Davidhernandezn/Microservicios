package com.microservicios.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EnableEurekaClient //OPCIONAL
@SpringBootApplication
//TAMBIEN EN SU CLASE PRINCIPAL AGREGARLE ENTITY SCAN DE LA ENTIDAD DESEADA
@EntityScan({ "com.microservicios.commons.alumnos.models.entity", 
			  "com.microservicios.cursos.models.entity" })

public class MicroserviciosCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCursosApplication.class, args);
	}

}
