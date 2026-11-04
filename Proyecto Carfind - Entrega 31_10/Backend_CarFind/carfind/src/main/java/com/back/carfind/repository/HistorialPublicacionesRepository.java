package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.HistorialPublicaciones;

@Repository
public interface HistorialPublicacionesRepository extends JpaRepository<HistorialPublicaciones, Long> {
}
