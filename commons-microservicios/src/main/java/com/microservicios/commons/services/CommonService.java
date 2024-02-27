package com.microservicios.commons.services;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//ES EL CONTRATO
public interface CommonService<E> { //E ES api PARA USAE LA GENERICS DE JAVA
public Iterable<E> findAll();
 
//PARA PAGINACION
/*PASAR PARAMETRO Pageable, Y TIPO DE RETORNO Page (importar de spring data domain)
 * ****PAGE ES EL RESULTADO
 * AQUI CONTENEMOS LA INFORMACION DE LA PAGINACION QUE QUEREMOS REALIZAR, ES DECIR
 * CUANDO MANDAMOS EL PAGE Y TAMAÑO
 * 
 * INTERNAMENTE SE HACE UN COUNT A LA BD Y REALIZA LA PAGINACION
 * */
public Page<E> findAll(Pageable pageable);
 


  //MANEJA OPCIONAL DE ALUMNO, RESULTADO SI EXISTE O NO
 public Optional<E>findById(Long id);
 
 //Retorna objeto Alumno contendra id (Recibealimno y lo retorna lo cguardado)
 public E save(E entity);//entity ES EL OBJETO GENERAL QUE SE TENDRÁ
 public void deleteById(Long id);
}
