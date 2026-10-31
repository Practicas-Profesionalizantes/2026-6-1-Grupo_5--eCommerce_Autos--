package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Encuentros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface EncuentrosRepository extends JpaRepository<Encuentros, Long> {
List<Encuentros> findByNombreContaining(String nombre);
} 