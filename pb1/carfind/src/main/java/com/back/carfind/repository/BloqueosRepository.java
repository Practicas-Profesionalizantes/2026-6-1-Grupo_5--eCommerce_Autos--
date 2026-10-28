package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Bloqueos;
import com.back.carfind.model.BloqueosId;

@Repository
public interface BloqueosRepository extends JpaRepository<Bloqueos, BloqueosId> {
}
