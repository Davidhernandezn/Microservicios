package com.microservicios.cursos.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.commons.examenes.models.entity.Examen;
import com.microservicios.commons.alumnos.models.entity.Alumno;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP) //TIMESTAMP FECHA A M D H
	private Date createAt;//IMPORTAR DE JAVA UTIL
	
	/*AGREGAR LIST de java util PARA ALUMNOS y generar su geter a seter*/
	//UN CURSO A MUCHOS ALUMNOS, EL FETCH ES CON CARGA PERESOZA DE TIPO LAZY
	//AL GENERAR EL JSON DEL OBJETO POJO DE LA ENTIDAD  
	//fetch REALIZA LA CONSULTA JPA CON SU RELACION QUE ES UN PROXY
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;
	
	/*RELACION MANY TO MANY
	 * UN CURSO PUEDE TENER MUCHOS EXAMENES
	 * UN EXAMEN PUEDE ESTAR ASOCIADO A MUCHOS CURSOS
	//ATRIBUTO PARA LA RELACIOM DEL CURSO CON LOS EXAMENES**/
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;
	
	
	//PARA LAS FECHAS
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	//CONSTRUCTOR ALUMNOS y examen, inicializarlos
	public Curso() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();//generar su getter and setter
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

//LISTA DE ALUMNO
	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
	//METODOS PARA AGREGAR Y ELIMINAR UN ALUMNO DEL CURSO, USO DE HANDLER
	//AGREGAR UNO A UNO ALUMNOS A LA LISTA
	public void addAlumno(Alumno alumno) {
		//VALIDAR QUE ALUMNOS ESTE definido hay que crear le su instancia
		this.alumnos.add(alumno);
	}
	
	//ELIMINAR UNO A UNO ALUMNOS A LA LISTA
	public void removeAlumno(Alumno alumno) {
		//VALIDAR QUE ALUMNOS ESTE definido hay que crear le su instancia
		this.alumnos.remove(alumno);
		///NOSE ELIMINA DE UNA ARRAY DIRECTAMENTE YA QUE VA A BUSCAR AL ALUMNOS PARA ELIMINARLO
		// ESTO ES EN ALUMNOS Y CON METODO EQUALS
		// *CREAR ALGORITMO PARA COMPARAR SI UN OBJETO ALUMNO ESTA DENTRO DE LA LISTA*
		//AHORA YA ESTA EN COMMONS ALUMNOS
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	
	//METODO ADD Y REMOVE DE EXAMENES
	/*EN CURSOS NECESITAMOS EL MICROSERVICIO EXAMENES
	 *PERO EN EXAMENES NO NECESITAMOS EL CURSO
	 *POR LO QUE NO NECESITAMOS LA RELACION INVERSA*/
	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	
	//necesitamos agregar en commons examenes el equals para eliminar examenes //IR A ENTITY EXAMEN (OVERRIDE)
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}
}

