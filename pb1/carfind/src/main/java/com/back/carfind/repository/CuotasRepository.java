package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Cuotas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CuotasRepository extends JpaRepository<Cuotas, Long> {
List<Cuotas> findByNombreContaining(String nombre);
} 