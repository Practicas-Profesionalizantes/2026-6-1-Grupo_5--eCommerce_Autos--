package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Reembolsos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ReembolsosRepository extends JpaRepository<Reembolsos, Long> {
List<Reembolsos> findByNombreContaining(String nombre);
}