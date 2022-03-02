package com.jagp.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagp.microservices.models.Estudiante;
import com.jagp.microservices.repository.IEstudianteRepository;

@Service
public class EstudianteService {
	
	@Autowired
	private IEstudianteRepository iEstudianteRepository;
	
	public Estudiante guardarEstudiante(Estudiante estudiante) {
		return iEstudianteRepository.save(estudiante);
	}
	
	public List<Estudiante> obtenerEstudiantes(){
		return iEstudianteRepository.findAll();
	}
	
	
	public Estudiante obtenerUnEstudiante(String id) {
		
		return iEstudianteRepository.findById(id).get();
	}

	public void softliminarEstudiante(String id) {
		iEstudianteRepository.softEliminarEstudiante(id);
	}
}
