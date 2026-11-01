package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Publicaciones;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {
}
