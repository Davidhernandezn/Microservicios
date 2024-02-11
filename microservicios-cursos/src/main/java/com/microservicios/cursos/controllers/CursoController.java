package com.microservicios.cursos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.microservicios.commons.controllers.CommonController;
import com.microservicios.cursos.models.entity.Curso;
import com.microservicios.cursos.services.CursoService;

//AGREGAR NOTACION RESTCONTROLLER
//EXTENDEMOS AL COMMON CONTROLLER Y AGREGAMOS ENTIDAD Y A NUESTRO SERVICE CREADO SIN TIPO
@RestController
public class CursoController extends CommonController<Curso, CursoService> {
	
	@PutMapping("/{id}")
	//? = generico que devolvera curso, 
	public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
		//PARA BUSACARLO A LA BD, tipo util
		java.util.Optional<Curso> o = this.service.findById(id);
		//validamos con present = booleado
		if(!o.isPresent()) {
			//RETORNAMOS 404 SIN CUERPO
			return ResponseEntity.notFound().build();
		}
		/*RECUPERAMOS Y GUARDAMOS CAMBIOS, 
		 * NO MANDAMOS LA FECHA QUE YA ESTA CREADA*/
		Curso dbCurso = o.get();
		dbCurso.setNombre(curso.getNombre());
		/*DEVOLVER UN STATUS 201 - CREATED Y PASAMOS DATO USANDO SERVICIO CON SAVE Y MANDAMOS EL DATO RECOLECTADO*/
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

}
