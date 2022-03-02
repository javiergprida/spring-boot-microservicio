package com.jagp.microservices.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "Profesor")
@Table(name = "\"Profesor\"", schema = "public")
@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_profesor_persona"))
public class Profesor extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "titulacion", nullable = false)
	private String titulacion;

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	@Column(name = "salario", nullable = false)
	private Double salario;
	
	@JsonIdentityInfo(
			generator = ObjectIdGenerators.PropertyGenerator.class,
			property = "key"
			)
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Curso> cursos;

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Profesor [titulacion=" + titulacion + ", getId()=" + getId() + ", getNombre()=" + getNombre()
				+ ", getGenero()=" + getGenero() + ", getEmail()=" + getEmail() + "]";
	}
	
	

}
