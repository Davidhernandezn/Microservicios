package com.microservicios.respuestas.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.microservicios.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {
	
	//RETORNAR LIST O ITERABLE DE RESPUESTA
	/*R ES EL OBJETO DE LA CLASE ENTITY ALIAS FETCH (PARA RETORNAR LA RESPUESTA CO EL ALUMNO Y EL EXAMEN)JOIN  R.ALUMNO
	 * INNER JOIN COMO EN BD COMPARAMOS CON REFERENCIA A LOS ATRIBUTOS DE LA RELACION */
	@Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);//COPEAR METODO Y AGREGARLO A RESPUESTA SERVICE ***
	
	/*CONSULTAR TODOS LOS EXAMENTES POR EL ALUMNO, Tendremos varias respuestas agupar por id del examen*/
	@Query("select e.id from Respuestas r join r.alumno a join r.pregunta p join p.examen e where a.id=?1 group by e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
