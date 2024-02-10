package com.microserviciosusuarios.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.commons.services.CommonServiceImp;
import com.microserviciosusuarios.models.entity.Alumno;
import com.microserviciosusuarios.models.repository.AlumnoRepository;

//crea componentes de spring debemos indicarlo
//para despues inyectarlo
@Service //NECESARIO PARA MANDAR EL REGISTRO A SPRINT COMO COMPONENTE
//public class AlumnoServiceImp implements AlumnoService {

//IMPLEMENTA ALUMNO SERVICE AHORA NECESITAMOS QUE EL SERVICEIMP HEREDE DEL PROYECTO GENERAL
public class AlumnoServiceImp extends CommonServiceImp<Alumno, AlumnoRepository> implements AlumnoService {
//E = ALUMNO
//CRUD REPOSITORI = AlumnoRepository <no es necesario agregar los tipos>
	
	//NO NECESARIO YA QUE SE TIENE EN LA PARTE GENERAL
	//@Autowired //para inyectar dependencias
	//private AlumnoRepository repository;//Tipo alumon repo y agregamos nombre
	
	/** TODO ESTO YA NO ES NECESARIO YA QUE SE TIENE EN LA PARTE GENERAL
	@Override
	//METODOS DE CONSULTA DE SOLO LECTURA
	@Transactional(readOnly = true) //de spring framework
	public Iterable<Alumno> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();//ya lo llamamos
	}

	@Override
	@Transactional(readOnly = true) //de spring framework
	public Optional<Alumno> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	@Transactional //PERMITE MODIFCAR LA TABLA
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		return repository.save(alumno);//ya lo llamamos
	}

	@Override
	@Transactional //PERMITE MODIFCAR LA TABLA
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
repository.deleteById(id);
	}
**/
}
