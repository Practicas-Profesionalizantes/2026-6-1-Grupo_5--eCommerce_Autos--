package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Preguntas;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Long> {
}
