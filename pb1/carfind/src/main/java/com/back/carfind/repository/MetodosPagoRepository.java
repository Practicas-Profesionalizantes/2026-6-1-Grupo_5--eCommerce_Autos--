package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.MetodosPago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MetodosPagoRepository extends JpaRepository<MetodosPago, Long> {
List<MetodosPago> findByNombreContaining(String nombre);
}