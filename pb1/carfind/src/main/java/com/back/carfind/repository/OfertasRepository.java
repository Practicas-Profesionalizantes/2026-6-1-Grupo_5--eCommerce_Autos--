package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Ofertas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface OfertasRepository extends JpaRepository<Ofertas, Long> {
List<Ofertas> findByNombreContaining(String nombre);
}