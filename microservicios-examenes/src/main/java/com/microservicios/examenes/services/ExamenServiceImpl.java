package com.microservicios.examenes.services;
import org.springframework.stereotype.Service;

import com.commons.examenes.models.entity.Examen;
import com.microservicios.commons.services.CommonServiceImp;
import com.microservicios.examenes.models.repository.ExamenRepository;


//CAMBIAR CRUD REPOSITORY POR EXAMEN REPOSITORY
@Service //PARA REGISTRARLO Y DESPUES INYECTARLO EN EL CONTROLADOR
public class ExamenServiceImpl extends CommonServiceImp<Examen, ExamenRepository> implements ExamenService {

//ELIMINAR METODOS GENERADOS PORQUE YA LOS TENEMOS

}
