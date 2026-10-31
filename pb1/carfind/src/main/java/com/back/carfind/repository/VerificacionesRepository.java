package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Verificaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface VerificacionesRepository extends JpaRepository<Verificaciones, Long> {
List<Verificaciones> findByNombreContaining(String nombre);
}