package com.microservicios.cursos.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.microservicios.cursos.models.entity.Curso;

//NOES NECESARIO AGREGAR UNA ANORACION YA QUE ES UN COMPONENTE SPRING Y SE PUEDE INYECTAR
public interface CursoRepository extends CrudRepository<Curso, Long>{

	//METODO PERZONALIZADO
	//RETORNAR CURSO ALIAS C Y USAR JOIN, USAR FECTH PARA POBLAR EL CURSO CON LA LISTA DE ALUMNOS EN UNA SOLA CONSULTA
	//NO ES PROXY, NECEITAMOS OBTENER ID DEL ALUMNO Y VALIDAR QUE SEA IGUAL AL PARAMETRO
	// c.alumnos = ES LA RELACION
	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	//RETORNAR CURSO POR ALUMNO
	public Curso findCursoByAlumnoId(Long id);	
}
