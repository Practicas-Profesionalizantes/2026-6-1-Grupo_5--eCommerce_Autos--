package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.ActividadUsuario;

@Repository
public interface ActividadUsuarioRepository extends JpaRepository<ActividadUsuario, Long> {
}
