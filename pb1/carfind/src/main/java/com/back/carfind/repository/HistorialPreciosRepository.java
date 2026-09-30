package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.HistorialPrecios;

@Repository
public interface HistorialPreciosRepository
        extends JpaRepository<HistorialPrecios, Long> {
}