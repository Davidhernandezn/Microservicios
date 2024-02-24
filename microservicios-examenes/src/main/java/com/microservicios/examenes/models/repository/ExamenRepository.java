package com.microservicios.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicios.examenes.models.entity.Examen;

//HEREDAR DE CRUD REPOSITORI
public interface ExamenRepository extends CrudRepository<Examen, Long>{

}
