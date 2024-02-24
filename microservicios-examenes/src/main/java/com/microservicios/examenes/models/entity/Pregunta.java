package com.microservicios.examenes.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//DECLARARLA COMO ENTITY
@Entity
//MAPEAMOS A UNA TABLA
@Table(name = "preguntas")
public class Pregunta {
	//NECESITA RELACION CON UN EXAMEN

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	//EN ESTE MOMENTO SE GENERA UN LOOP INFINIFO = EXAMEN - PREGUNTAS PARA ESO USAR JSONIGNOREPROPERTIES
		//ESTO SUPRIME ATRIBUTOS = preguntas en el examen 
		@JsonIgnoreProperties(value = {"preguntas"}) //ESTABLECER LIMITE - EN LA CONSTRUCCION DEL JSON = IGNORAR LA RELACION INVERSA

	
	//RELACION
	//MANI MUCHAS PREGUNTAS UN EXAMEN
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examen_id")//LLAVE FORANEA (ESTABLECE REALACION ENTRE PREGUNTA Y EXAMEN)
	private Examen examen;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	
	/*SOBRESCRIBIR UN METODO - EQUALS
	 * */
	@Override
	public boolean equals(Object obj) {
		//PREGUATMOS SI LA INSTANCIA ES IHGUAL AL OBJECTE QUE PASAMOS POR ARGUMENTO EN EL API COLLECTION EN METODO REMOVE
		if(this == obj) {
			return true;//LO ENCUENTRA Y LO ELIMINA
		}
		//VALIDAR QUE LA INSTANCIA SEA UN OBJETO DE ALUMNO
			if(!(obj instanceof Pregunta)) {
				return false;
			}
		
		//VALIDAR SI LA INSTANCIA ES DISTINTA PERO EL OBJETO ES EL MISMO POR EL ID
			//cast de obj a tipo alumno para comparar ID
			Pregunta a = (Pregunta)obj;
			//SI CUMPLE LO ELIMINA
			return this.id != null && this.id.equals(a.getId());
		}
	}