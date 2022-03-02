package com.jagp.microservices.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jagp.microservices.models.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, String>{
	
	@Transactional
	@Modifying
	@Query("UPDATE Persona pe SET pe.state = false WHERE pe.id=:id")
	void softEliminarPersona(@Param("id") String id);

}
