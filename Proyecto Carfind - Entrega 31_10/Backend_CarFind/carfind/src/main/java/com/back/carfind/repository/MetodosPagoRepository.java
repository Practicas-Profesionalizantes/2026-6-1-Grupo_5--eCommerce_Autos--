package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.MetodosPago;

@Repository
public interface MetodosPagoRepository extends JpaRepository<MetodosPago, Long> {
}
