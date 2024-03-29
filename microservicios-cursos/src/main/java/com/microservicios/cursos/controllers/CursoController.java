package com.microservicios.cursos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.examenes.models.entity.Examen;
import com.microservicios.commons.alumnos.models.entity.Alumno;
import com.microservicios.commons.controllers.CommonController;
import com.microservicios.cursos.models.entity.Curso;
import com.microservicios.cursos.services.CursoService;

//AGREGAR NOTACION RESTCONTROLLER
//EXTENDEMOS AL COMMON CONTROLLER Y AGREGAMOS ENTIDAD Y A NUESTRO SERVICE CREADO SIN TIPO
@RestController
public class CursoController extends CommonController<Curso, CursoService> {
	
	@PutMapping("/{id}")
	//? = generico que devolvera curso, 
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		//VALIDA SI HAY ERRORES
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
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
	
	
	//asignar varios alumnos en un curso en el front
	//ASIGNAR
	@PutMapping("/{id}/asignar-alumnos")
	//PASAMOS PATH VARIABLE = {id}, pasar en el cuerpo del request el json que contenga
	//UN ARREGLO DE ALUMNOS CADA UNO CON SU ID
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
		
		//BUSCAR EL CURSO CON EL ID
		java.util.Optional<Curso> o = this.service.findById(id);
		//validamos con present = booleado
		if(!o.isPresent()) {
			//RETORNAMOS 404 SIN CUERPO
			return ResponseEntity.notFound().build();
		}
		//OBTENEMOS EL CURSO
		Curso dbCurso = o.get();
		//ITERAR ALUMNOS Y AGREGARLOS AL CURSO
		//LAMDAS (FUNCION ANONIMA), SE RECIBE AL ALUMNO Y HACEMOS ALGO
		alumnos.forEach(a ->{
			dbCurso.addAlumno(a);
		});
		//GUARDAR EL CURSO CON LOS ALUMNOS ASIGNADOS
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	
	//ELIMINAR
	@PutMapping("/{id}/eliminar-alumno")
	//PASAMOS PATH VARIABLE = {id}, pasar en el cuerpo del request el json que contenga
	//UN ARREGLO DE ALUMNOS CADA UNO CON SU ID
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		
		//BUSCAR EL CURSO CON EL ID
		java.util.Optional<Curso> o = this.service.findById(id);
		//validamos con present = booleado
		if(!o.isPresent()) {
			//RETORNAMOS 404 SIN CUERPO
			return ResponseEntity.notFound().build();
		}
		//OBTENEMOS EL CURSO
		Curso dbCurso = o.get();
		dbCurso.removeAlumno(alumno);
		//GUARDAR EL CURSO CON LOS ALUMNOS ASIGNADOS
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	
	//OBTENER CURSOS DE ALUMNO
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}
	
	
		//ASIGNAR Examenes
		@PutMapping("/{id}/asignar-examenes")
		//PASAMOS PATH VARIABLE = {id}, pasar en el cuerpo del request el json que contenga
		//UN ARREGLO DE ALUMNOS CADA UNO CON SU ID
		public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
			
			//BUSCAR EL CURSO CON EL ID
			java.util.Optional<Curso> o = this.service.findById(id);
			//validamos con present = booleado
			if(!o.isPresent()) {
				//RETORNAMOS 404 SIN CUERPO
				return ResponseEntity.notFound().build();
			}
			//OBTENEMOS EL CURSO
			Curso dbCurso = o.get();
			//ITERAR ALUMNOS Y AGREGARLOS AL CURSO
			//LAMDAS (FUNCION ANONIMA), SE RECIBE AL ALUMNO Y HACEMOS ALGO
			examenes.forEach(e ->{
				dbCurso.addExamen(e);
			});
			//GUARDAR EL CURSO CON LOS examenes ASIGNADOS
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
		}
		
		
		//ELIMINAR examen
		@PutMapping("/{id}/eliminar-examen")
		//PASAMOS PATH VARIABLE = {id}, pasar en el cuerpo del request el json que contenga
		//UN ARREGLO DE ALUMNOS CADA UNO CON SU ID
		public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
			
			//BUSCAR EL CURSO CON EL ID
			java.util.Optional<Curso> o = this.service.findById(id);
			//validamos con present = booleado
			if(!o.isPresent()) {
				//RETORNAMOS 404 SIN CUERPO
				return ResponseEntity.notFound().build();
			}
			//OBTENEMOS EL CURSO
			Curso dbCurso = o.get();
			dbCurso.removeExamen(examen);
			//GUARDAR EL CURSO CON LOS ALUMNOS ASIGNADOS
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
		}
}
