package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Variantes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface VariantesRepository extends JpaRepository<Variantes, Long> {
List<Variantes> findByNombreContaining(String nombre);
}