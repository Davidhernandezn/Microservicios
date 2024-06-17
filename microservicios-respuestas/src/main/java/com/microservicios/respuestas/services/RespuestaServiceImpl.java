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
		// TODO Auto-generated method stub
		return repository.saveAll(respuestas);
	}

}
