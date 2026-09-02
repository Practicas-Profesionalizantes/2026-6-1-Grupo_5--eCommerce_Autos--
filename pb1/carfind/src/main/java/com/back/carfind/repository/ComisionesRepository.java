package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Comisiones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ComisionesRepository extends JpaRepository<Comisiones, Long> {
List<Comisiones> findByNombreContaining(String nombre);
} 