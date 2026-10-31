package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Promociones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PromocionesRepository extends JpaRepository<Promociones, Long> {
List<Promociones> findByNombreContaining(String nombre);
}