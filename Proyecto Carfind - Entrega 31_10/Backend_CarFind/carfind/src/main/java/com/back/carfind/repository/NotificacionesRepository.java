package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Notificaciones;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {
}
