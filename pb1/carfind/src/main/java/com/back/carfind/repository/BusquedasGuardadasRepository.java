package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.BusquedasGuardadas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BusquedasGuardadasRepository extends JpaRepository<BusquedasGuardadas, Long> {
List<BusquedasGuardadas> findByNombreContaining(String nombre);
} 