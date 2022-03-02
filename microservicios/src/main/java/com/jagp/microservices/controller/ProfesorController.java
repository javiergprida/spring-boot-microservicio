package com.jagp.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jagp.microservices.models.Profesor;
import com.jagp.microservices.service.PersonaService;
import com.jagp.microservices.service.ProfesorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/colegio")
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private PersonaService personaService;
	
	@Operation(summary="crear un profesor")
	@PostMapping("/profesor")
	public Profesor crearProfesor(@Validated @RequestBody Profesor profesor) {
		return profesorService.guardarProfesor(profesor);
	}
	
	@Operation(summary="listar profesores")
	@GetMapping("/profesores")
	public List<Profesor> listarProfesores(){
		return profesorService.obtenerProfesores();
	}
	
	@Operation(summary="listar un profesor por salario y numero de cursos")
	@PostMapping("/profesores/custom-query")
	public List<Profesor> listarProfesoresPersonalizado(Double salario, Integer numero_cursos){
		return profesorService.obtenerProfesoresPersonalizado(salario, numero_cursos);
	}
	
	@Operation(summary="actualizar un profesor")
	@PutMapping("/profesor/{id}")
	public Profesor actualizarProfesor(@PathVariable String  id,@Validated @RequestBody Profesor profesor) {
		return profesorService.guardarProfesor(profesor);
	}
	
	@Operation(summary="eliminar un profesor")
	@DeleteMapping("/profesor/{id}")
	public void eliminarProfesor(@PathVariable String  id) {
		personaService.eliminarPersona(id);
	}
	
	@Operation(summary="obtener un profesor por id")
	@GetMapping("/profesor/{id}")
	public Profesor encontrarProfesor(@PathVariable String id) {
		
		return profesorService.obtenerUnProfesor(id);
	}
}
