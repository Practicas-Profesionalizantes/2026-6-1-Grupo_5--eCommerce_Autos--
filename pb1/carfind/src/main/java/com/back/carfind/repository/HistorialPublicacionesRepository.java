package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.HistorialPublicaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface HistorialPublicacionesRepository extends JpaRepository<HistorialPublicaciones, Long> {
List<HistorialPublicaciones> findByNombreContaining(String nombre);
}