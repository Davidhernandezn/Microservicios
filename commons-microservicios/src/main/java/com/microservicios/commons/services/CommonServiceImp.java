package com.microservicios.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//crea componentes de spring debemos indicarlo
//para despues inyectarlo
//@Service YA NO SE NECESITA PORQUE YA NO LO VAMOS A INYECTAR VAMOS HEREDAR
//AGREGAMOS EL TIPO DE DATO GENERIC Y CRUD REPOSITORY
//INTERFAZ QUE HEREDE DE CRUD REPOSITORY <E, R CrudRepository<E,Long>
//R solo es un nombre
//MI CLASE entity ya no es alumno si no E
//CrudRepository ESTA ENLAZDO CON E
public class CommonServiceImp <E, R extends CrudRepository<E,Long>> implements CommonService<E> {

	@Autowired //para inyectar dependencias
	//USAR PROTECTED PARA REUTILZIAR EN LA CLASES HIJAS COMO ALUMNOS 
	//USAR PRIVATE CUANDO SON INDEPENDIENTES
	protected R repository;//Tipo alumon repo y agregamos nombre
	@Override
	//METODOS DE CONSULTA DE SOLO LECTURA
	@Transactional(readOnly = true) //de spring framework
	public Iterable<E> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();//ya lo llamamos
	}

	@Override
	@Transactional(readOnly = true) //de spring framework
	public Optional<E> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	@Transactional //PERMITE MODIFCAR LA TABLA
	public E save(E entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);//ya lo llamamos
	}

	@Override
	@Transactional //PERMITE MODIFCAR LA TABLA
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
repository.deleteById(id);
	}

}
