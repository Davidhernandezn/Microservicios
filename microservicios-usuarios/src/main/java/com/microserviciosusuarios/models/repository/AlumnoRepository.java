package com.microserviciosusuarios.models.repository;
import org.springframework.data.repository.CrudRepository;
import com.microserviciosusuarios.models.entity.Alumno;
//DESPUES DE EXTENDS INDICAMOS CLASE ENTITY Y TIPO DE LA ID
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
//podemos implementar nuestros propios metodos
	
}