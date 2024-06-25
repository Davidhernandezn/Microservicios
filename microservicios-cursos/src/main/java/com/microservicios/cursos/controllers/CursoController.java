package com.microservicios.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
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

	//CON LO QUE TESTEAREMOS EL BALANCEO DE CARGAS,  config.balanceador.test ES VARIABLE DE ENTORNO EN PROPERTIES

	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	
	@PutMapping("/{id}")
	//? =   que devolvera curso, 
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
	//CURSO TIENE RELACION MUCHOS A MUCHOS CON LOS EXAMENNES, HAY QUE ITERAR CUAL EXAMEN FUE RESPONDIDO
	//CONSULTAR TODOS LOS EXAMENES Y DESPUES ITERAR POR CADA
	//ATRIBUTO EN EXAMEN PARA INDICAR SI FUE RESPONDIDO (NO PUEDE SER APLICADO A EXAMEN PARA APLICAR SOLO UN ALUMNO, *un atributi que no debe mapearse*
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		
		//AGREGAREMOS LA COMUNICACION DE LOS MICROSERVICIOS USANDO FAIN
		//VALIDAR QUE CURSO SEA DISTINTO DE NULL
		if(curso != null) {
			//ITERAR SOBRE LOS EXAMENES PARA VERSI ESTAN RESPONDIDOS
			List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestaAlumno(id);//RETORNA UN ITERABLE PERO LO CONVERTIMOS A LIST
			
			//STREAM PARA FLUJOS Y PROGRAMACION FUNCIONAL, (MANIPULAR LISTAS, FLUJOS, ETC)
			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if (examenesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen; //SIEMPRE RETORNA STREM POR LO QUE DEBE REGRESARLO COMO DECEAS
			}).collect(Collectors.toList());//LISTA
			curso.setExamenes(examenes);//PASAMOS EXAMENES
		}
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

		//Balanceo de cargas
		@GetMapping("/balanceador-test")
		public ResponseEntity<?> listar() {
			//PASAR VALOR DE LA CONSTANTE, INTECTAR PARA TESTEAR EL BALANCEO DE CARGA
			Map<String, Object> response = new HashMap<String, Object>(); //TIPO DEL PARAMETRO Y VALOR = RESPUESTA
			response.put("Balanceador", balanceadorTest);
			//AGREGAR LISTADO
			response.put("Cursos", service.findAll());			
			return ResponseEntity.ok(response);
		}
		
		
}
