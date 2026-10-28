package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.TarjetasUsuario;

@Repository
public interface TarjetasUsuarioRepository extends JpaRepository<TarjetasUsuario, Long> {
}
