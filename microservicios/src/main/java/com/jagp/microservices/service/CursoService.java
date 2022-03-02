package com.jagp.microservices.service;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagp.microservices.models.Curso;
import com.jagp.microservices.repository.ICursoRepository;

@Service
public class CursoService {

	@PersistenceContext
	private EntityManager cursoManager;

	@Autowired
	private ICursoRepository iCursoRepository;

	public Curso guardarProfesor(Curso curso) {
		return iCursoRepository.save(curso);
	}

	public List<Curso> obtenerCursos() {
		return iCursoRepository.findAll();
	}

	
	public List<Curso> obtenerCursosDisponibles(String fecha_inicio) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate _fecha_inicio =  LocalDate.parse(fecha_inicio, formatter);
		
		Filter filter = cursoManager.unwrap(Session.class).enableFilter("filterByDate");
		
		filter.setParameter("_fecha_inicio", _fecha_inicio);
		
		List<Curso> cursosDisponibles = iCursoRepository.findAll();
		
	    cursoManager.unwrap(Session.class).disableFilter("filterByDate");
		return cursosDisponibles;
	}

	public void eliminarCurso(Long id) {
		iCursoRepository.deleteById(id);
	}

	public Curso obtenerUnCurso(Long id) {

		return iCursoRepository.findById(id).get();
	}

}
