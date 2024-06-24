package com.microservicios.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableEurekaClient //OPCIONAL
@EnableFeignClients //IMPORTAR HABILITAR PARA OBTENER EXAMENES RESPONDIDOS
@SpringBootApplication
//TAMBIEN EN SU CLASE PRINCIPAL AGREGARLE ENTITY SCAN DE LA ENTIDAD DESEADA
/*
 * Esta anotación le dice a la aplicación dónde buscar clases que 
 * representan entidades de la base de datos.
 * */
@EntityScan({ "com.microservicios.commons.alumnos.models.entity", 
			  "com.commons.examenes.models.entity",
			  "com.microservicios.cursos.models.entity"})

public class MicroserviciosCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCursosApplication.class, args);
	}
}
