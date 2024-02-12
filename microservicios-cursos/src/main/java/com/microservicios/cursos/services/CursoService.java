package com.microservicios.cursos.services;

import com.microservicios.commons.services.CommonService;
import com.microservicios.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {
	//METODO PERZONALIZADO DE REPOSITORY	
	public Curso findCursoByAlumnoId(Long id);	

}
