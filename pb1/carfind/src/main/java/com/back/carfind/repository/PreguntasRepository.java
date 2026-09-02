package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Preguntas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Long> {
List<Preguntas> findByNombreContaining(String nombre);
}