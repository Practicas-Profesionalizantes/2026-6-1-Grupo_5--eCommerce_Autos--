package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.EstadisticasPublicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface EstadisticasPublicacionRepository extends JpaRepository<EstadisticasPublicacion, Long> {
List<EstadisticasPublicacion> findByNombreContaining(String nombre);
} 