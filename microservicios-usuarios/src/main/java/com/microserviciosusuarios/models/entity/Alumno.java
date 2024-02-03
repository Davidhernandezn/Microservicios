package com.microserviciosusuarios.models.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

//persistencia mapeada a una tabla
@Entity
//opcional por que se toma el nombre de la clase en singular
//
@Table(name="alumnos")
public class Alumno {

	@Id //llave
	//auto incremental,identity, sequence,
	//PARA POSGREST, identity, sequence,
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	//NO ES NECESARIO AGREGAR @COLUM YA QUE SE LLAMA IGUAL, SOLO SI QUEREMOS CONFIGURAR PARAMETROS VARCAHR SIZE, ETC
	private String nombre;
	private String apellido;
	private String email;
	
	@Column(name = "create_at") //asi se separa en sql o bd
	private Date createAt;//import java util
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}

