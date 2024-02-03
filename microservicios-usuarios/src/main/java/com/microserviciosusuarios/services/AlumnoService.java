package com.microserviciosusuarios.services;
import java.util.Optional;
import com.microserviciosusuarios.models.entity.Alumno;

//ES EL CONTRATO
public interface AlumnoService {
 public Iterable<Alumno> findAll();
 
 //MANEJA OPCIONAL DE ALUMNO, RESULTADO SI EXISTE O NO
 public Optional<Alumno>findById(Long id);
 
 //Retorna objeto Alumno contendra id (Recibealimno y lo retorna lo cguardado)
 public Alumno save(Alumno alumno);
 public void deleteById(Long id);
}
