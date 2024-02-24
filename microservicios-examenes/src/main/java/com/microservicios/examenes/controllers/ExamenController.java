package com.microservicios.examenes.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
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
		//OBTENER EXAMEN POR ID
		Optional<Examen> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();//RESPUESTA VACIA
		}
		Examen examenDb = o.get(); //SI ESTA PRESENTE OBTENEMOS EL EXAMEN
		examenDb.setNombre(examen.getNombre());//MODIFICAMOS NOMBRE, obtenemos del request del json que nos envian
		
		//validar preguntas que existen en la BD pero no exixten en el array, o talvez estan en el json pero no en la BD
		//reflejar cambio
		//EJE_1 - crear un arraylist de preuntas eliminadas e iterar con foreach y preguntas si exixten en el JSON  enviado
		
		
	/** REDUCIR CON EL STREAM DE JAVA 8
		List<Pregunta> eliminadas = new ArrayList<>();
		//PREGUNTAR POR CADA PREGUNTA SI EXISTE EN EL JSON
		examenDb.getPreguntas().forEach(pdb -> {

			//PREGUNTAS DE JSON LAS VALIDAMOS CON LAS PDB VEMOS SI EXIXTEN EN GET PREGUNTAS QUE NOS ENVIARON
			if (!examen.getPreguntas().contains(pdb)) {
				//SI NO EXISTE SE AGREAN PARA ELIMINAR
				eliminadas.add(pdb);
			}
		});
		**/
		
		
		
		/*USANDO STREAMS*/
		//CREA 2 STRING UNO CON FILTRO
		//LO QUE SE EMITE CON EL FILTRO LO VALIDAMOS CON EL CONTAINS YA QUE TIENE UNA CONDICION
		examenDb.getPreguntas()//OBTENGO PREGUNTAS
		.stream()//CONVIERTO A UN STREAM DE JAVA 8
		.filter(pdb -> !examen.getPreguntas().contains(pdb))//FILTRAMOS Y RETORNA UN NUEVO STREAM APARTIR DE UNA CONDICION
		.forEach(examenDb::removePregunta);		
	
		/**FORMA 1
		eliminadas.forEach(p -> {
			examenDb.removePregunta(p);//ELIMINAR CADA UNA,//LLAMA EL METODO REMOVE
		});**/
		/*
		 * REDUCIDO
		 * eliminadas.forEach(examenDb::removePregunta);
		 * 
		 * */
		
		//AGREGAR NUEVAS PREGUNTAS Y MODIFICAR EXISTENTES Y DEJAR COMO ESTABAN LAS QUE NO SE TOCARON
		examenDb.setPreguntas(examen.getPreguntas());
		//GUARDAMOS
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
}
