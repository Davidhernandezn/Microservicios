package com.microservicios.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.respuestas.models.entity.Respuesta;
import com.microservicios.respuestas.models.repository.RespuestaRepository;

@Service//PARA REGISTRARLO COMO COMPONENTE DE SPRING
public class RespuestaServiceImpl implements RespuestaService {
	
	@Autowired //PARA INYECTAR EL REPOSITORY
	private RespuestaRepository repository;
	@Override
	@Transactional //DE SPRING FRAMEWORK
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}
	
	@Override
	@Transactional(readOnly = true) //DE SPRING FRAMEWORK - SOLO DE CONSULTA
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);//EL QUERY IMPLEMENTADO AHORA LO LLAMAMOS
	}

	@Override
	@Transactional(readOnly = true) //DE SPRING FRAMEWORK - SOLO DE CONSULTA
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		/*MICROSERVICIO CURSO SE COMUNICARA CON EL API PARA OBTENER LA LISTA ITERABLE CON LOS ID DE EXAMENES RESPONDIDOS*/
		return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
