package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.PagosCuotas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PagosCuotasRepository extends JpaRepository<PagosCuotas, Long> {
List<PagosCuotas> findByNombreContaining(String nombre);
}