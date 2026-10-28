package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Direcciones;

@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Long> {
}
