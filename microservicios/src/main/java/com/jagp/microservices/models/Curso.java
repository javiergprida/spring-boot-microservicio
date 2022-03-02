package com.jagp.microservices.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name = "Curso")
@Table(name = "\"Curso\"", schema = "public")
@FilterDef(name = "filterByDate", parameters = @ParamDef(name = "_fecha_inicio", type = "LocalDate"))
@Filters(@Filter(name = "filterByDate" , condition = "fecha_inicio>=:_fecha_inicio"))
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	// Key = 1, 2, 3, ...
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long key;

	@ManyToOne
	@JoinColumn(name = "profesor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_profesor_curso", foreignKeyDefinition = "FOREIGN KEY (profesor_id)\r\n"
			+ "REFERENCES public.\"Profesor\" (id) MATCH SIMPLE\r\n" + "ON UPDATE CASCADE\r\n" + "ON DELETE CASCADE", value = ConstraintMode.CONSTRAINT))
	private Profesor profesor;

	@Column(name = "nombre_curso", nullable = false, unique = true)
	private String nombreCurso;

	
	@Column(name = "fecha_inicio", nullable = false, columnDefinition = "date DEFAULT 'now()'")
	private LocalDate fecha_inicio = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")).toLocalDate();

	@Column(name = "fecha_culminacion", nullable = false)
	private LocalDate fecha_culminacion;
	
	@JsonIdentityInfo(
			generator = ObjectIdGenerators.PropertyGenerator.class,
			property = "id"
			)
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToMany(mappedBy = "cursos_inscritos", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Estudiante> estudiantes_inscritos;

	public Long getKey() {
		return key;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<Estudiante> getEstudiantes_inscritos() {
		return estudiantes_inscritos;
	}

	public void setEstudiantes_inscritos(List<Estudiante> estudiantes_inscritos) {
		this.estudiantes_inscritos = estudiantes_inscritos;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_culminacion() {
		return fecha_culminacion;
	}

	public void setFecha_culminacion(LocalDate fecha_culminacion) {
		this.fecha_culminacion = fecha_culminacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Curso [key=" + key + ", profesor=" + profesor + ", nombreCurso=" + nombreCurso + ", fecha_inicio="
				+ fecha_inicio + ", fecha_culminacion=" + fecha_culminacion + "]";
	}
	
	

}
