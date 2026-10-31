package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.PlanesCuotas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PlanesCuotasRepository extends JpaRepository<PlanesCuotas, Long> {
List<PlanesCuotas> findByNombreContaining(String nombre);
}