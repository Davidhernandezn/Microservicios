package com.microservicios.examenes.services;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commons.examenes.models.entity.Examen;
import com.microservicios.commons.services.CommonServiceImp;
import com.microservicios.examenes.models.repository.ExamenRepository;


//CAMBIAR CRUD REPOSITORY POR EXAMEN REPOSITORY
@Service //PARA REGISTRARLO Y DESPUES INYECTARLO EN EL CONTROLADOR
public class ExamenServiceImpl extends CommonServiceImp<Examen, ExamenRepository> implements ExamenService {
//ELIMINAR METODOS GENERADOS PORQUE YA LOS TENEMOS

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		// TODO Auto-generated method stub
		return repository.findByNombre(term);
	}


}
