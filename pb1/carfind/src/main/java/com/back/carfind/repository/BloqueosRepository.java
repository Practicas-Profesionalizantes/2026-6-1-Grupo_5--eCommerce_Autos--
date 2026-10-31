package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Bloqueos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BloqueosRepository extends JpaRepository<Bloqueos, Long> {
List<Bloqueos> findByNombreContaining(String nombre);
} 