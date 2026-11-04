package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.HistorialVehiculo;

@Repository
public interface HistorialVehiculoRepository extends JpaRepository<HistorialVehiculo, Long> {
}
