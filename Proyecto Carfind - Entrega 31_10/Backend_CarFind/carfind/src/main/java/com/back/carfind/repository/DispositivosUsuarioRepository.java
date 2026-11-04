package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.DispositivosUsuario;

@Repository
public interface DispositivosUsuarioRepository extends JpaRepository<DispositivosUsuario, Long> {
}
