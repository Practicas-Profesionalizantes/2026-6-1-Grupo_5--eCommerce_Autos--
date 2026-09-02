package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Direcciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Long> {
List<Direcciones> findByNombreContaining(String nombre);
} 