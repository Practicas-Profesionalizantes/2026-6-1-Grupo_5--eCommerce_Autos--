package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Modelos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ModelosRepository extends JpaRepository<Modelos, Long> {
List<Modelos> findByNombreContaining(String nombre);
}