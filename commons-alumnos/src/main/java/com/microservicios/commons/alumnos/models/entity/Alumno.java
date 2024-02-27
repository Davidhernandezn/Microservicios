package com.microservicios.commons.alumnos.models.entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



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
	
	//REGLAS DE VALIDATION
	//NO ES NECESARIO AGREGAR @COLUM YA QUE SE LLAMA IGUAL, SOLO SI QUEREMOS CONFIGURAR PARAMETROS VARCAHR SIZE, ETC
	@NotEmpty //QUE NO SEA VACIO
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
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

	
	
	//SOBRE ESCRIBIR UN METODO	de la clase object
	@Override
	public boolean equals(Object obj) {
	//PREGUATMOS SI LA INSTANCIA ES IHGUAL AL OBJECTE QUE PASAMOS POR ARGUMENTO EN EL API COLLECTION EN METODO REMOVE
	if(this == obj) {
		return true;//LO ENCUENTRA Y LO ELIMINA
	}
	//VALIDAR QUE LA INSTANCIA SEA UN OBJETO DE ALUMNO
		if(!(obj instanceof Alumno)) {
			return false;
		}
	
	//VALIDAR SI LA INSTANCIA ES DISTINTA PERO EL OBJETO ES EL MISMO POR EL ID
		//cast de obj a tipo alumno para comparar ID
		Alumno a = (Alumno)obj;
		//SI CUMPLE LO ELIMINA
		return this.id != null && this.id.equals(a.getId());
	}
	

}
