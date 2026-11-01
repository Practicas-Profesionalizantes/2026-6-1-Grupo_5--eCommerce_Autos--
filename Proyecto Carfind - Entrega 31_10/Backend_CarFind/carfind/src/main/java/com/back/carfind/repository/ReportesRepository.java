package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Reportes;

@Repository
public interface ReportesRepository extends JpaRepository<Reportes, Long> {
}
