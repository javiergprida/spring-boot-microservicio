package com.jagp.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagp.microservices.repository.IPersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	private  IPersonaRepository  iPersonaRepository;
	
	/*public Persona guardarEstudiante(Persona persona) {
		return iPersonaRepository.save(persona);
	}
	
	public List<Persona> obtenerEstudiantes(){
		return iPersonaRepository.findAll();
	}*/
	
	public void eliminarPersona(String id) {
		iPersonaRepository.deleteById(id);
	}
	
	public void softliminarPersona(String id) {
		iPersonaRepository.softEliminarPersona(id);
	}
	

}
