package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Marcas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MarcasRepository extends JpaRepository<Marcas, Long> {
List<Marcas> findByNombreContaining(String nombre);
}