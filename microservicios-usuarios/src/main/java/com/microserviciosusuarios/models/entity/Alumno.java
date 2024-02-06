package com.microserviciosusuarios.models.entity;

import java.util.Date;



//persistencia mapeada a una tabla
@javax.persistence.Entity
//opcional por que se toma el nombre de la clase en singular
//
@javax.persistence.Table(name="alumnos")
public class Alumno {

	@javax.persistence.Id //llave
	//auto incremental,identity, sequence,
	//PARA POSGREST, identity, sequence,
	@javax.persistence.GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;
	//NO ES NECESARIO AGREGAR @COLUM YA QUE SE LLAMA IGUAL, SOLO SI QUEREMOS CONFIGURAR PARAMETROS VARCAHR SIZE, ETC
	private String nombre;
	private String apellido;
	private String email;
	
	@javax.persistence.Column(name = "create_at") //asi se separa en sql o bd
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)//FECHA COMPLETA Y HORA
	private Date createAt;//import java util
	@javax.persistence.PrePersist
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