package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.PlanesCuotas;

@Repository
public interface PlanesCuotasRepository extends JpaRepository<PlanesCuotas, Long> {
}
