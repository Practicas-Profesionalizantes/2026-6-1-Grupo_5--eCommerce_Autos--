package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Notificaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {
List<Notificaciones> findByNombreContaining(String nombre);
}