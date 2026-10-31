package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.VisitasAuto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface VisitasAutoRepository extends JpaRepository<VisitasAuto, Long> {
List<VisitasAuto> findByNombreContaining(String nombre);
}