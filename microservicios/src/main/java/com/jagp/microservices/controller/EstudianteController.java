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

import com.jagp.microservices.models.Estudiante;
import com.jagp.microservices.service.EnvioMailService;
import com.jagp.microservices.service.EstudianteService;
import com.jagp.microservices.service.PersonaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/colegio")
public class EstudianteController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private EnvioMailService envioMailService;
	
	@Operation(summary="crear un estudiande")
	@PostMapping("/estudiante")
	public Estudiante crearEstudiante(@Validated @RequestBody Estudiante estudiante) {
		
		Estudiante newEstudiante =  estudianteService.guardarEstudiante(estudiante);
		if(newEstudiante != null) {
			
			envioMailService.sendEmail(newEstudiante.getEmail(), "Inscripcion registrada exitosamente!!", newEstudiante.toString());
		}
		
			
			return newEstudiante;
	}
	@Operation(summary="listar estudiandes")
	@GetMapping("/estudiantes")
	public List<Estudiante> listarEstudiantes(){
		return estudianteService.obtenerEstudiantes();
	}
	
	@Operation(summary="obtener un estudiante por id")
	@GetMapping("/estudiante/{id}")
	public Estudiante encontrarEstudiante(@PathVariable String id) {
		
		return estudianteService.obtenerUnEstudiante(id);
	}
	
	@Operation(summary="actualizar un estudiande")
	@PutMapping("/estudiante/{id}")
	public Estudiante actualizarEstudiante(@PathVariable String  id,@Validated @RequestBody Estudiante estudiante) {
		return estudianteService.guardarEstudiante(estudiante);
	}
	
	@Operation(summary="eliminar un estudiande(persona) de forma logica")
	@PutMapping("/estudiantes/{id}")
	public void deshabilitarEstudiante(@PathVariable String  id) {
		estudianteService.softliminarEstudiante(id);
		personaService.softliminarPersona(id);
	}
	
	@Operation(summary="eliminar un estudiande")
	@DeleteMapping("/estudiante/{id}")
	public void eliminarEstudiante(@PathVariable String  id) {
		personaService.eliminarPersona(id);
	}
}
