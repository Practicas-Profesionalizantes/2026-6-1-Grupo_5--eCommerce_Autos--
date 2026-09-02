package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Publicaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {
List<Publicaciones> findByNombreContaining(String nombre);
}