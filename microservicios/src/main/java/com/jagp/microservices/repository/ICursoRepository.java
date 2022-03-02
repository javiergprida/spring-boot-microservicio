package com.jagp.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jagp.microservices.models.Curso;


@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long>{

}
