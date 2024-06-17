package com.microservicios.respuestas.services;

import com.microservicios.respuestas.models.entity.Respuesta;

public interface RespuestaService {
	/*GUARDA VARIAS RESPUESTAS Y LAS DEVUELVE EN JSON DE MANERA PERSISTIDA*/
	public Iterable <Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);//AL SER COPEDAO SOLICITARA IMPLEMENTAR METODO

		
}
