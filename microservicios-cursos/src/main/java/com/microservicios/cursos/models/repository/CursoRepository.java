package com.microservicios.cursos.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicios.cursos.models.entity.Curso;

//NOES NECESARIO AGREGAR UNA ANORACION YA QUE ES UN COMPONENTE SPRING Y SE PUEDE INYECTAR
public interface CursoRepository extends CrudRepository<Curso, Long>{

}
