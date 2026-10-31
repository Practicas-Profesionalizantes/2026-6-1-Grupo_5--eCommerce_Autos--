package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Sesiones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface SesionesRepository extends JpaRepository<Sesiones, Long> {
List<Sesiones> findByNombreContaining(String nombre);
}