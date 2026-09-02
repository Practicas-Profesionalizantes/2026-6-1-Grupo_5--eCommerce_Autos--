package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Movimientos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
List<Movimientos> findByNombreContaining(String nombre);
}