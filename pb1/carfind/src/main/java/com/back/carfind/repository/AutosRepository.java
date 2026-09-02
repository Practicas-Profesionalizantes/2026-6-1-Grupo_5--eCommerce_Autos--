package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Autos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface AutosRepository extends JpaRepository<Autos, Long> {
List<Autos> findByNombreContaining(String nombre);
} 