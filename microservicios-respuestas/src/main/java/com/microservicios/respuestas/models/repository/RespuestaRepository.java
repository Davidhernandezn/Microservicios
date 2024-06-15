package com.microservicios.respuestas.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicios.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

}
