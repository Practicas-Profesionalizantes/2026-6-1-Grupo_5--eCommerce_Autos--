package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Cuotas;

@Repository
public interface CuotasRepository extends JpaRepository<Cuotas, Long> {
}
