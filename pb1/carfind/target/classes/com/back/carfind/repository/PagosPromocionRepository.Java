package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.PagosPromocion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PagosPromocionRepository extends JpaRepository<PagosPromocion, Long> {
List<PagosPromocion> findByNombreContaining(String nombre);
}