package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Inventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
List<Inventario> findByNombreContaining(String nombre);
}