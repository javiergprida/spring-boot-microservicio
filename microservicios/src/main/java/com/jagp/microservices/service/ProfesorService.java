package com.jagp.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jagp.microservices.models.Profesor;
import com.jagp.microservices.repository.IProfesorRepository;

@Service
public class ProfesorService {

	@Autowired
	private IProfesorRepository iProfesorRepository;

	public Profesor guardarProfesor(Profesor profesor) {
		return iProfesorRepository.save(profesor);
	}

	public List<Profesor> obtenerProfesores() {
		return iProfesorRepository.findAll();
	}

	public Profesor obtenerUnProfesor(String id) {

		return iProfesorRepository.findById(id).get();
	}
	
	public List<Profesor> obtenerProfesoresPersonalizado(Double salario, Integer numero_cursos) {
		return iProfesorRepository.consultaPersonalizadaProfesores(salario,numero_cursos);
	}

}
