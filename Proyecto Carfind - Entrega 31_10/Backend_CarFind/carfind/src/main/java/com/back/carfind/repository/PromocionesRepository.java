package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Promociones;

@Repository
public interface PromocionesRepository extends JpaRepository<Promociones, Long> {
}
