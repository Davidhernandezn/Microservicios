package com.microserviciosusuarios.controllers;
import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservicios.commons.alumnos.models.entity.Alumno;
import com.microservicios.commons.controllers.CommonController;
import com.microserviciosusuarios.services.AlumnoService;

@RestController//contrrolador de tipo rest para restfull, para Json o XML
//ompuesto por controller y response body, lo objetos retornados se convierten en JSON

//E = GENERICO (ALUMNO)
//AlumnoService MANDAR EL SERVICIO SIN EL TIPO
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
	//VER IMAGEN
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		//BUSCAMOS ALUMNO POR ID Y MODIFICAMOS LOS DATOS EL ALUMNO CON LOS DATOS DEL JSON 
		Optional<Alumno> o = service.findById(id);
		
		//VALIDAR SI EXIXTE y si hay foto null
		if(o.isEmpty() || o.get().getFoto() == null) {
			//SI ESTA VACIO
			return ResponseEntity.notFound().build();//CONSTRUYE RESPUESTA DEL VACIO
		}
		
		//PASAR FOTO A LA RESPUESTA - RECURSO DE SPRING
		//importar de spring core
		Resource imagen = new ByteArrayResource(o.get().getFoto());

		//RETORNAMOS 
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
	
	
	
	//ESTO YA ESTA INDICADO EN EL GENERAL
	//@Autowired //
	//private AlumnoService service;//inyectar service
	
	//AGREGAMOS RUTA YA QUE NO ESTA EN LA RAIZ
	@PutMapping("/{id}") //PASAMOS EL ID 
	//METODO PARA CAPTURAR PARAMETRO ID Y EL alumno QUE QUEREMOS GUARDAR
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id){
		
		//VALIDA SI HAY ERRORES
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		
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
	
	
	
	//METODO PERZONALIZADO
	@GetMapping("/filtrar/{termino}")
	//termino VALIDAR SI ES DISTINTO DEL ARGUMENTO SI ES DIFERENTE HAY QUE SEÑALARLO = @PathVariable()
	public ResponseEntity<?> filtrar(@PathVariable  String termino){
		return ResponseEntity.ok(service.findByNombreOrApellido(termino));//mandamos services y el parametro
	}


//CARAGA DE IMAGEN O ARCHIVOS
	//@Override SE ELIMINAR POR QUE CAMBIA EL NOMBRE DEL METODO
	@PostMapping("/crear-con-foto")
	//NO ENVIAMOS EL //@RequestBody ENVIA JSON CON LOS DATOS. PERO PARA LOS ARCHIVOS ENVIAMOS PARAMETROS MultipartFile
	public ResponseEntity<?> crearConFoto(Alumno alumno, BindingResult result, 
			@RequestParam MultipartFile archivo) throws IOException {
		// VALIDAMOS QUE VENGA EL ARCHIVO
		if(!archivo.isEmpty()) {
			//asignamos al alumno y pasamos los bytes
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	
	
	//AGREGAMOS EDITAR CON FOTO RUTA YA QUE NO ESTA EN LA RAIZ
		@PutMapping("/editar-con-foto/{id}") //PASAMOS EL ID 
		//METODO PARA CAPTURAR PARAMETRO ID Y EL alumno QUE QUEREMOS GUARDAR
		public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id, 
				@RequestParam MultipartFile archivo) throws IOException{
			
			//VALIDA SI HAY ERRORES
			if(result.hasErrors()) {
				return this.validar(result);
			}
			
			
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
			
			// VALIDAMOS QUE VENGA EL ARCHIVO
			if(!archivo.isEmpty()) {
				//asignamos al alumno y pasamos los bytes
				alumnoDb.setFoto(archivo.getBytes());
			}
			
			//ANTES DE PASARLO AL BODY LO DEBEMOS GUARDAR Y PERSISTIR
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
		}
		
	
	
}
