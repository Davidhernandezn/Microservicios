package com.commons.examenes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//IMPORTAL MANUALMENTE YA QUE SE IMPORTA CON SPRING WEB 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//DECLARARLA COMO ENTITY
@Entity
//MAPEAMOS A UNA TABLA
@Table(name = "examenes")
public class Examen {

	//NECESITA RELACION CON PREGUNTAS (HIJO PREGUNTA => PADRE EXAMEN)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;
	
	//TIPO LIST YA QUE SE TENDRAN MUCHAS PREGUNTAS
	//ONE TO MANY un examen muchas preguntas
	/*EL JSON SE GENERA A PARTIR DE LOS GETTERS AL INVOCAR GET REALIZA LA CONSULTA A PREGUNTAS RELACIONADAS 
	 * CASCADE - ELIMINAR EXAMEN TAMBIEN ELIMINA PREGUNTAS
	 * PERSIST - CREAR EXAMEN Y CREARLE SUS PREGUNTAS
	 * ALL PORQUE APLICA REMOVE
	 * ORPHAN REMOVAL - TRUE - AL ELIMINAR PREUGNTA DE LA LISTA DEBE ASIGNAR REFERENCIA DEL EXAMEN A NULL FOREIG KEY = NULL
	 * 							SI UNA PREGUNTA NO ESTA RELACIONADO A UN EXAMEN LA FORENIA ES NULL Y LO ELIMINA
	 * 
	 * RELACION BIIDIRECCIONAL : (mappedBy = "examen" - RELACION INVERSA atributo de la contraparte examen en el entity pregunta*/
	
	//EN ESTE MOMENTO SE GENERA UN LOOP INFINIFO = EXAMEN - PREGUNTAS PARA ESO USAR JSONIGNOREPROPERTIES
	//ESTO SUPRIME ATRIBUTOS = EXAMEN 
	@JsonIgnoreProperties(value = {"examen"}, allowSetters = true) //ESTABLECER LIMITE - EN LA CONSTRUCCION DEL JSON = IGNORAR LA RELACION INVERSA
	//allowSetters = true - PARA QUE AL OCULTAR O SUPRIMIR ATRIBUTO EXAMEN NOS PERMITE ASIGNAR POR GETTER AND SETTER 
	@OneToMany(mappedBy = "examen" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //FETCH TIPO LAZY - carga perezosa (AL )
	
	private List<Pregunta> preguntas;//COMO ES LIST HAY QUE INICIALIZARLO (HACERLO EN UN CONSTRUCTOR)
	
	public Examen() {
		//inicializamos array list
		this.preguntas = new ArrayList<>();
	}

	//INICIALIZAR FECHA
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	//GET A SET DE LA NUEVA PROPIEDAD AGREGADA
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	//ERROR - VALIDAR QUE AL ASIGNAR PREGUNTA LO ASIGNE A EXAMEN TAMBIEN (RELACION INVERSA)
	public void setPreguntas(List<Pregunta> preguntas) {
		//GARBAGE COLLECTOR SE PIERDE Y LOS OBJETOS A ELIMINAR - ELIMINAR Y ASIGNAR DE NUEVO LAS NUEVAS PREGUNTAS
		this.preguntas.clear();//REINICIAR THIS.PREGUNTAS, SET ASIGNA PREGUNTAS Y MODIFICA LISTADO DE PREGUNTAS 

		//lamdas
		preguntas.forEach(p -> {
			//POR CADA PREGUNTA ENVIA SE ASIGNA AL EXAMEN USANDO ADD PREGUNTA
			this.addPregunta(p);
			
			/*lamda simplificado
			 * preguntas.forEach(p -> this.addPregunta(p);)
			 * 
			 * lamda mas simplificado java 8
			 * preguntas.forEach(this::addPregunta);
			 * */
		});;
	}
	
	//METODO AGREGAR PREGUNTAS //GUARDA PREGUNTA EN EL ARRAYLIST Y ASIGNA EXAMEN
	public void addPregunta(Pregunta pregunta) {
		this.preguntas.add(pregunta);
		//RELACION INVERSA (SI NO SE AGREGA FUNCIONA BIEN PERO LA FOREING KEY ESTARA VACIA O NULL
		//this para usar la misma instancia
		pregunta.setExamen(this);//AGREAR PREGUNTA AL EXAMEN - CADA PREGUNTA QUE SE AGREGA DEBEMOS INCLUIRLO AL EXAMEN
	}
	
	//METODO ELIMINAR PREGUNTAS - QUITAR PREGUNTA Y RELACION
	/*EQUALS -- VALIDAR QUE EXISTA PREGUNTA EN LA LISTA - COMPARAR CON ID*/
	public void removePregunta(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
		//RELACION INVERSA (SI NO SE AGREGA FUNCIONA BIEN PERO LA FOREING KEY ESTARA VACIA O NULL
		//this para usar la misma instancia
		pregunta.setExamen(null);//QUITAR RELACION
	}

	
	//PREGUNTAR ID Y VALIDAR POR ID
	@Override
	public boolean equals(Object obj) {
		//PREGUATMOS SI LA INSTANCIA ES IHGUAL AL OBJECTE QUE PASAMOS POR ARGUMENTO EN EL API COLLECTION EN METODO REMOVE
		if(this == obj) {
			return true;//LO ENCUENTRA Y LO ELIMINA
		}
		//VALIDAR QUE LA INSTANCIA SEA UN OBJETO DE ALUMNO
			if(!(obj instanceof Examen)) {
				return false;
			}
		
		//VALIDAR SI LA INSTANCIA ES DISTINTA PERO EL OBJETO ES EL MISMO POR EL ID
			//cast de obj a tipo alumno para comparar ID
			Examen a = (Examen)obj;
			//SI CUMPLE LO ELIMINA
			return this.id != null && this.id.equals(a.getId());
	}
	
	
}
