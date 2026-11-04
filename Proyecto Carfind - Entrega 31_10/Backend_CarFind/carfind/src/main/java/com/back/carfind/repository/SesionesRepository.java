package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Sesiones;

@Repository
public interface SesionesRepository extends JpaRepository<Sesiones, Long> {
}
