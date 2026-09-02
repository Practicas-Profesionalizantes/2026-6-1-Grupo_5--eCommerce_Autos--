package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Informe_dominio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface Informe_dominioRepository extends JpaRepository<Informe_dominio, Long> {
List<Informe_dominio> findByNombreContaining(String nombre);
} 