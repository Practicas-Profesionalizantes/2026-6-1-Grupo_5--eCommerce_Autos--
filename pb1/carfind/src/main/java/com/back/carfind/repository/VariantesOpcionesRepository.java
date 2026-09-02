package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.VariantesOpciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface VariantesOpcionesRepository extends JpaRepository<VariantesOpciones, Long> {
List<VariantesOpciones> findByNombreContaining(String nombre);
}