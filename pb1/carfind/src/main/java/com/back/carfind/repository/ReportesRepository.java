package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Reportes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ReportesRepository extends JpaRepository<Reportes, Long> {
List<Reportes> findByNombreContaining(String nombre);
}