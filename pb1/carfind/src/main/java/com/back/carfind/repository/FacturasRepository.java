package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Facturas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface FacturasRepository extends JpaRepository<Facturas, Long> {
List<Facturas> findByNombreContaining(String nombre);
} 