package com.jagp.microservices.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jagp.microservices.models.Estudiante;


@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, String>{

	@Transactional
	@Modifying
	@Query("UPDATE Estudiante e SET e.estado = 0 WHERE e.id=:id")
	void softEliminarEstudiante(@Param("id") String id);
}
