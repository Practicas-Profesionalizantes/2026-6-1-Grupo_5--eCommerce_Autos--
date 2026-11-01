package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Movimientos;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
}
