package com.microserviciosusuarios.services;

import com.microservicios.commons.alumnos.models.entity.Alumno;
import com.microservicios.commons.services.CommonService;

//import java.util.Optional;
//import com.microserviciosusuarios.models.entity.Alumno;

//ES EL CONTRATO
//FASE 2 CON EL GENERAL mandar ENTITY YA GENERADO
public interface AlumnoService extends CommonService<Alumno>{
// public Iterable<Alumno> findAll();
 
 //MANEJA OPCIONAL DE ALUMNO, RESULTADO SI EXISTE O NO
 //public Optional<Alumno>findById(Long id);
 
 //Retorna objeto Alumno contendra id (Recibealimno y lo retorna lo cguardado)
 //public Alumno save(Alumno alumno);
 //public void deleteById(Long id);
}
