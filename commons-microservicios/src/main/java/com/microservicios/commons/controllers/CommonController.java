package com.microservicios.commons.controllers;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservicios.commons.services.CommonService;

//NO VA POR SER GENERICA
//@RestController//contrrolador de tipo rest para restfull, para Json o XML
//ompuesto por controller y response body, lo objetos retornados se convierten en JSON

//E = TIPO GENERICO
//S = TIPO SERVICE HEREDA DE MI COMMON SERVICE ya cuenta con E
public class CommonController <E, S extends CommonService<E>>{
	@Autowired 
	//PROTECTED PARA LA HERENCIA - PARA REUTILIZAR SERVICE ENE L CONTROLADOR
	protected S service;//inyectar service
	
	@GetMapping //PERMITE MAPEAR URL AL METODO, DECIMOS QUE ES LA RAIZ
	//? PARA CUALQUIER TIPO
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());//retornar de forma statica
	}

	//para buscar por el ID usamos whycat parametro de la ruta porque irá cambiando
	@GetMapping("/{id}")
	//Spring lo extrae con path variable
	//@PathVariable(variable o name) usar por si se llama diferente a id  
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		if(o.isEmpty()) {
			//SI ESTA VACIO
			return ResponseEntity.notFound().build();//CONSTRUYE RESPUESTA DEL VACIO
		}
		return ResponseEntity.ok(o.get());//SI EXISTE LO MANDA 
	}
	
	
	//POST MAPPING NO TIENE RUTA 
	@PostMapping
	//INDICAR DE DONDE VIENE EL DATO QUE DEBEMOS PERSISTIR, HACER CONSULTAS
	//EL JSON SE POBLA EN ALUMNO, SI SON LOS MISMOS , los datos se pueblen en alumno
	public ResponseEntity<?> crear(@RequestBody E entity){
		//lo que retorna
		E entityDb = service.save(entity);
		//status tipo created 201, 
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}

	
	/** ESTE METODO POSIBLEMENTE NO VA YA QUE PUEDE VAREAR EN CADA ENTITY AL IGUAL QUE LOS OTROS
	 * DEBERIA IR EN SU CONTROLADOR
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
	}**/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		//PRIMERO ELIMINAR
		service.deleteById(id);
		return ResponseEntity.noContent().build();//204 
		
	}
	
}
