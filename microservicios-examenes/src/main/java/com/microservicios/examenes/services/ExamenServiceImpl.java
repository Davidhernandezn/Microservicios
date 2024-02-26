package com.microservicios.examenes.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commons.examenes.models.entity.Asignatura;
import com.commons.examenes.models.entity.Examen;
import com.microservicios.commons.services.CommonServiceImp;
import com.microservicios.examenes.models.repository.AsignaturaRepository;
import com.microservicios.examenes.models.repository.ExamenRepository;


//CAMBIAR CRUD REPOSITORY POR EXAMEN REPOSITORY
@Service //PARA REGISTRARLO Y DESPUES INYECTARLO EN EL CONTROLADOR
public class ExamenServiceImpl extends CommonServiceImp<Examen, ExamenRepository> implements ExamenService {
//ELIMINAR METODOS GENERADOS PORQUE YA LOS TENEMOS
	
	/*PARA ASIGNARTURAS, NECESITAMOS INTECTAR*/
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	//IMPLEMENT DEL SERIVE PARA BUSCAR
	@Override
	@Transactional(readOnly = true)
	//HACIENDO CAST A LA LIST RETURN O USAR ITERABLE, AL FINAL ITERABLE ES GENERICO 
	public Iterable<Asignatura> findAllAsignaturas() {
		//NECESITAMOS ACCEDER AL REPOSITORI DE LA ASIGNATURA
		return asignaturaRepository.findAll();
	}


}
