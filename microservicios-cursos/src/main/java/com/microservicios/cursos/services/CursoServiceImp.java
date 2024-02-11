package com.microservicios.cursos.services;

import org.springframework.stereotype.Service;

import com.microservicios.commons.services.CommonServiceImp;
import com.microservicios.cursos.models.entity.Curso;
import com.microservicios.cursos.models.repository.CursoRepository;

//AGREGAR ENTITY Y EL REPOSITORY
//AGREGAR SERVICE PARA QUE SE REGUSTRE COMO COMPONENTE DE SPRING Y LO PODAMOS INYECTAR
@Service 
public class CursoServiceImp extends CommonServiceImp<Curso, CursoRepository> implements CursoService {


}
