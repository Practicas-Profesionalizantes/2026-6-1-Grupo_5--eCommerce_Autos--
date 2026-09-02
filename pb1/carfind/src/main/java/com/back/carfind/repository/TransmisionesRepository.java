package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Transmisiones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TransmisionesRepository extends JpaRepository<Transmisiones, Long> {
List<Transmisiones> findByNombreContaining(String nombre);
}