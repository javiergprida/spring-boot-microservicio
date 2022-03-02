package com.jagp.microservices.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity(name = "Persona")
@Table(name = "\"Persona\"", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@Where(clause="estado = true")
public class Persona {

	@Id
	@Column(name = "id", length = 10)
	private String id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	// sexo = F(femenino) o M(masculino)...
	@Column(name = "genero", nullable = false, length = 1)
	private String genero;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "state", columnDefinition = "boolean DEFAULT 'true'")
	private Boolean state = true;

	
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
