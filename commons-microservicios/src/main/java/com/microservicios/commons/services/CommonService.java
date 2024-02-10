package com.microservicios.commons.services;
import java.util.Optional;

//ES EL CONTRATO
public interface CommonService<E> { //E ES api PARA USAE LA GENERICS DE JAVA
 public Iterable<E> findAll();
 
 //MANEJA OPCIONAL DE ALUMNO, RESULTADO SI EXISTE O NO
 public Optional<E>findById(Long id);
 
 //Retorna objeto Alumno contendra id (Recibealimno y lo retorna lo cguardado)
 public E save(E entity);//entity ES EL OBJETO GENERAL QUE SE TENDRÁ
 public void deleteById(Long id);
}
