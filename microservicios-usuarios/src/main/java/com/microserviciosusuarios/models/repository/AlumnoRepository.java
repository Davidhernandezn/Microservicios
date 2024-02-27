package com.microserviciosusuarios.models.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservicios.commons.alumnos.models.entity.Alumno;
//DESPUES DE EXTENDS INDICAMOS CLASE ENTITY Y TIPO DE LA ID
//public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

/*PAGINACION
 * cambiar a PagingAndSortingRepository*/
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

//podemos implementar nuestros propios metodos
	
	//HACER UN METODO PERZONALIZADO POR NOMBRE DE METODO O UN QUERY
	//REVISAR EN DOCUMENTACION DE SPRING DATA JPA
	
	//IMPORTAR DE JPA REPOSITORY
	//CONSULTA HIBERNATE O JPA QUERY LAGUAGE - CONOCIDO POR: HQL o JPAQL
	//BUSCAR Y RETORNAR ENTITY ALUMNO, ALIAS A WHERE Y PASAMO LIKE
	//CONSULTA
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	
	//METODO
	public List<Alumno> findByNombreOrApellido(String termino);
}