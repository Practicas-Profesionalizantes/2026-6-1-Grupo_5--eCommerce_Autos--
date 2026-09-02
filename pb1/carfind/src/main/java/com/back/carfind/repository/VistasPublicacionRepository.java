package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.VistasPublicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface VistasPublicacionRepository extends JpaRepository<VistasPublicacion, Long> {
List<VistasPublicacion> findByNombreContaining(String nombre);
}