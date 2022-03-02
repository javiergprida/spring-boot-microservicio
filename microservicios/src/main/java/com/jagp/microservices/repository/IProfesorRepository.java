package com.jagp.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.jagp.microservices.models.Profesor;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, String>{

	@Query(value = "SELECT p FROM Profesor p WHERE p.salario>=:salario AND SIZE (p.cursos)>=:numero_cursos")
	List<Profesor> consultaPersonalizadaProfesores(@Param("salario") Double salario, @Param("numero_cursos") Integer numero_cursos);
}
