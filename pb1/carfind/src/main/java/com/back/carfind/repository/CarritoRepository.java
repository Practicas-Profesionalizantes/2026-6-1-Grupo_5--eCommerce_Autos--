package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Carrito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
List<Carrito> findByNombreContaining(String nombre);
} 