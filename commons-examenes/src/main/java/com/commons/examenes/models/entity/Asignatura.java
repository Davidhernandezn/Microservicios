package com.commons.examenes.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*asignaturas pueden tener subasignaturas EN BD = SELF JOIN (UNA ABLA ESTA RELACIONADA ASI MISMA)
 * */
@Entity
@Table(name= "asignaturas")
public class Asignatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	//RELACION INVERSA
	@JsonIgnoreProperties(value = {"hijos"}, allowSetters = true)
	@ManyToOne//MUCHOS HIJOS PUEDEN SER ASOCIADOS A UN PADRE
	private Asignatura padre;
	
	//UNA ASIGNATURA MUCHOS HIJOS
	/*
	 * mappedBy - RELACION INVERSA MAPEADO POS EL ATRINUTO PADRE, 
	 * CASCADE - SI ELIMINAS PADRE - LIMINAR HIJOS, SI CREAS PADRE - DAR HIJOS  
	 * */
	
	/*JSON
	 *  value es arrehlo indicar atrobutos a omitir y permitir setters*/

	@JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL) //
	private List<Asignatura> hijos; //SIMPRE INICIALIZAR EN UN CONSTRUCTOR

	public Asignatura() {
		this.hijos = new ArrayList<>();
		// TODO Auto-generated constructor stub
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
	
	//generar getter and setter
	public Asignatura getPadre() {
		return padre;
	}

	public void setPadre(Asignatura padre) {
		this.padre = padre;
	}

	public List<Asignatura> getHijos() {
		return hijos;
	}

	public void setHijos(List<Asignatura> hijos) {
		this.hijos = hijos;
	}
	
}
