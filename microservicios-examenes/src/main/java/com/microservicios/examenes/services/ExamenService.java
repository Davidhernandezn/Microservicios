package com.microservicios.examenes.services;

import java.util.List;

import com.commons.examenes.models.entity.Examen;
import com.microservicios.commons.services.CommonService;

//extender de commons
public interface ExamenService extends CommonService<Examen>{
	//AGREGAR DEPENDENCIAS COMMON SERVICES
	public List<Examen>findByNombre(String term);

}
