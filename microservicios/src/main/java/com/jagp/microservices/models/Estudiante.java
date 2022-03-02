package com.jagp.microservices.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Estudiante")
@Table(name = "\"Estudiante\"", schema = "public")
@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_estudiante_persona"))
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Estudiante extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "beca", columnDefinition = "boolean DEFAULT 'false'")
	private Boolean beca = false;

	// Estado = 1:Inscrito, 2:Retirado, 3:Graduado...
	@Column(name = "estado", columnDefinition = "integer DEFAULT '1'")
	private Integer estado = 1;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "\"curso_estudiante\"", joinColumns = @JoinColumn(name = "estudiante_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_curso_estudiante", foreignKeyDefinition = "FOREIGN KEY (estudiante_id)\r\n"
			+ "REFERENCES public.\"Estudiante\"(id) MATCH SIMPLE\r\n" + "ON UPDATE CASCADE\r\n"
			+ "ON DELETE CASCADE", value = ConstraintMode.CONSTRAINT)),

			inverseJoinColumns = @JoinColumn(name = "curso_key", referencedColumnName = "key", foreignKey = @ForeignKey(name = "fk_estudiante_curso", foreignKeyDefinition = "FOREIGN KEY (curso_key)\r\n"
					+ "REFERENCES public.\"Curso\"(key) MATCH SIMPLE\r\n" + "ON UPDATE CASCADE\r\n"
					+ "ON DELETE CASCADE", value = ConstraintMode.CONSTRAINT)),

			uniqueConstraints = @UniqueConstraint(name = "key_compuesta", columnNames = { "estudiante_id",
					"curso_key" }))
	private List<Curso> cursos_inscritos;

	

	public Boolean getBeca() {
		return beca;
	}



	public void setBeca(Boolean beca) {
		this.beca = beca;
	}



	public Integer getEstado() {
		return estado;
	}



	public void setEstado(Integer estado) {
		this.estado = estado;
	}



	public List<Curso> getCursos_inscritos() {
		return cursos_inscritos;
	}



	public void setCursos_inscritos(List<Curso> cursos_inscritos) {
		this.cursos_inscritos = cursos_inscritos;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		
		String beca_descripcion = "";
		String estado_descripcion = "";
		
		if(beca) {
			beca_descripcion = "Aplica";
			
		}else {
			beca_descripcion = "No Aplica";
		}
		
		if(estado == 1) {
			estado_descripcion = "Inscrito";
			
		}else {
			estado_descripcion = "No Inscrito";
		}
		
		return "Estudiante: \nBeca= " + beca_descripcion + ", \nEstado= " + estado_descripcion + ", \nCursos inscritos= " + cursos_inscritos
				+ ", \nDNI= " + getId() + ", \nNombre= " + getNombre() + ", \nGenero= " + getGenero()
				+ ", \nEmail= " + getEmail() + "]";
	}
	
	

}
