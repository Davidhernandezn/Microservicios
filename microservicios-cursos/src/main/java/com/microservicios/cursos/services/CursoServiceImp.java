package com.microservicios.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.commons.services.CommonServiceImp;
import com.microservicios.cursos.clients.RespuestaFeignClient;
import com.microservicios.cursos.models.entity.Curso;
import com.microservicios.cursos.models.repository.CursoRepository;

//AGREGAR ENTITY Y EL REPOSITORY
//AGREGAR SERVICE PARA QUE SE REGUSTRE COMO COMPONENTE DE SPRING Y LO PODAMOS INYECTAR
@Service 
public class CursoServiceImp extends CommonServiceImp<Curso, CursoRepository> implements CursoService {

	//INYECTAR RESPUESTA FAINH CLIENT
	@Autowired
	private RespuestaFeignClient client;
	
	@Transactional(readOnly = true)
	@Override
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	//NO ES NECESARIO AGREGAR TRANSACCIONAL YA QUE NO ES REPOSITORY NI CONEXION A BD
	//SI FALLA EL TRANSACCIONAL - LA COMUCIONC LANZA EXCEPTION
	//AQUI HARÁ ROLLBAKC POR QUE NO TIENE TRANSACCIONAL SOLO COMUNICAION
	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestaAlumno(alumnoId);
	}
}
