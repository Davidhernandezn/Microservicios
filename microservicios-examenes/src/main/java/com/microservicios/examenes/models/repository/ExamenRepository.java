package com.microservicios.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.commons.examenes.models.entity.Examen;

//HEREDAR DE CRUD REPOSITORI
public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long>{
//BUSCAR EL EXAMEN POR NOMBRE
	//RETORNAMOS LOS EXAMENES CUANDO EL OBJETO SEA IGUAL A LA DERECHA O IZQUIERDA INGRESADO
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen>findByNombre(String term);
	
}
