package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.ActividadSospechosa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ActividadSospechosaRepository extends JpaRepository<ActividadSospechosa, Long> {
List<ActividadSospechosa> findByNombreContaining(String nombre);
}