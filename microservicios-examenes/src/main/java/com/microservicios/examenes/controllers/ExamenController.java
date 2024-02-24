package com.microservicios.examenes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.commons.controllers.CommonController;
import com.microservicios.examenes.models.entity.Examen;
import com.microservicios.examenes.services.ExamenService;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService>{
//YA CONTAMOS CON LOS METODOS HANDLER DEL CONTROLADOR
	
	//METODO PUT YA QUE FALTA POR QUE PUEDE VAREAR
	@PutMapping("/{id}")
	public ResponseEntity<?> editar (@RequestBody Examen examen, @PathVariable Long id){
		return null;
	}
}
