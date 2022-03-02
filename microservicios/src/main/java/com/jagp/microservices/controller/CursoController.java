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

import com.jagp.microservices.models.Curso;
import com.jagp.microservices.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/colegio")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Operation(summary="crear un curso")
	@PostMapping("/curso")
	public Curso crearCurso(@Validated @RequestBody Curso curso) {
		return cursoService.guardarProfesor(curso);
	}
	
	@Operation(summary="obtener un curso por su fecha de inicio")
	@GetMapping("/cursos/{fecha_inicio}")
	public List<Curso> listarCursosPorFecha(@PathVariable String fecha_inicio){
		return cursoService.obtenerCursosDisponibles(fecha_inicio);
	}
	
	@Operation(summary="listar cursos")
	@GetMapping("/cursos")
	public List<Curso> listarCursos(){
		return cursoService.obtenerCursos();
	}
	
	@Operation(summary="actualizar un curso")
	@PutMapping("/curso/{id}")
	public Curso actualizarCurso(@PathVariable Long  id,@Validated @RequestBody Curso curso) {
		return cursoService.guardarProfesor(curso);
	}
	
	@Operation(summary="eliminar un curso")
	@DeleteMapping("/curso/{id}")
	public void eliminarCurso(@PathVariable Long  id) {
		cursoService.eliminarCurso(id);
	}
	
	@Operation(summary="obtener un curso por id")
	@GetMapping("/curso/{id}")
	public Curso encontrarCurso(@PathVariable Long id) {
		
		return cursoService.obtenerUnCurso(id);
	}
}
