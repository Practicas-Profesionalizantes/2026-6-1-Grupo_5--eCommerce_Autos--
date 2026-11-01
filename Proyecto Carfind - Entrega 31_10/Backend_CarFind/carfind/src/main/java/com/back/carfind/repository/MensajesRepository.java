package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Mensajes;

@Repository
public interface MensajesRepository extends JpaRepository<Mensajes, Long> {
}
