package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.HistorialVehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface HistorialVehiculoRepository extends JpaRepository<HistorialVehiculo, Long> {
List<HistorialVehiculo> findByNombreContaining(String nombre);
} 