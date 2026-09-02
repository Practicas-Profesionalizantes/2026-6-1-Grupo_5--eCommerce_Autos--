package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Tipo_combustible;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TipoCombustibleRepository extends JpaRepository<Tipo_combustible, Long> {
List<Tipo_combustible> findByNombreContaining(String nombre);
}