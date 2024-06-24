package com.microservicios.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservicios-respuestas")//MICROSERVICIO CON EL QUE TE VAS A CONECTAR
public interface RespuestaFeignClient {
	
	//AQUE ENDPOINT SE VA A CONECTAR, PARAMETROS Y SU RETORNO, PUEDER SER UN REQUEST BODY
	//YA CUENTA CON BALANCEO DE CARGAS UTILIZA REBEN
	//POR TIEMPO DE RESPUESTAM ALETATOIO, ETC
	//PARA CONSUMIR Y COMUNICARSE
	
	//AGREGARLO AL SERVICE
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos") //TIPO DE REQUEST A REALIZAR, PARA VER LOS EXAMENES RESPONDIDOS
	public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(@PathVariable Long alumnoId);

}
