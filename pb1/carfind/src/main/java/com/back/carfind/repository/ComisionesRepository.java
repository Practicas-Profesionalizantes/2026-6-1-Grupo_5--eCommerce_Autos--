package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Comisiones;

@Repository
public interface ComisionesRepository extends JpaRepository<Comisiones, Long> {
}
