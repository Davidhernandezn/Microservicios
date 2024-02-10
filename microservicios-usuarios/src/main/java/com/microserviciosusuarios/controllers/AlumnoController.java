package com.microserviciosusuarios.controllers;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.commons.controllers.CommonController;
import com.microserviciosusuarios.models.entity.Alumno;
import com.microserviciosusuarios.services.AlumnoService;

@RestController//contrrolador de tipo rest para restfull, para Json o XML
//ompuesto por controller y response body, lo objetos retornados se convierten en JSON

//E = GENERICO (ALUMNO)
//AlumnoService MANDAR EL SERVICIO SIN EL TIPO
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
	//ESTO YA ESTA INDICADO EN EL GENERAL
	//@Autowired //
	//private AlumnoService service;//inyectar service
	
	//AGREGAMOS RUTA YA QUE NO ESTA EN LA RAIZ
	@PutMapping("/{id}") //PASAMOS EL ID 
	//METODO PARA CAPTURAR PARAMETRO ID Y EL alumno QUE QUEREMOS GUARDAR
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){
	
		//BUSCAMOS ALUMNO POR ID Y MODIFICAMOS LOS DATOS EL ALUMNO CON LOS DATOS DEL JSON 
		Optional<Alumno> o = service.findById(id);
		
		//VALIDAR SI EXIXTE
		if(o.isEmpty()) {
			//SI ESTA VACIO
			return ResponseEntity.notFound().build();//CONSTRUYE RESPUESTA DEL VACIO
		}
		
		//SI EXISTE LO AGREGAMOS
		Alumno alumnoDb = o.get(); //OBTENEMOS ALUMNO
		//SOLO MANDAR LO QUE QUEREMOS CAMBIAR
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		//MANTENER FECHA DE CREACION EL SISTEMA LO MANEJA SOLO
		
		//ANTES DE PASARLO AL BODY LO DEBEMOS GUARDAR Y PERSISTIR
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
}
