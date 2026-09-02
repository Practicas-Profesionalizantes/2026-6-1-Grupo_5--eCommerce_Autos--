package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.TiposPromocion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TiposPromocionRepository extends JpaRepository<TiposPromocion, Long> {
List<TiposPromocion> findByNombreContaining(String nombre);
}