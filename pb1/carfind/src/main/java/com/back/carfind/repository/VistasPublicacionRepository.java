package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.VistasPublicacion;
import com.back.carfind.model.VistasPublicacionId;

@Repository
public interface VistasPublicacionRepository extends JpaRepository<VistasPublicacion, VistasPublicacionId> {
}
