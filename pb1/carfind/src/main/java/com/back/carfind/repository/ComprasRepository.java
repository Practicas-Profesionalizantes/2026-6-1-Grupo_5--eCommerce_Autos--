package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Compras;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ComprasRepository extends JpaRepository<Compras, Long> {
List<Compras> findByNombreContaining(String nombre);
} 