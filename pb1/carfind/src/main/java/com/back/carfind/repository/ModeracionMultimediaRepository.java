package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.ModeracionMultimedia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ModeracionMultimediaRepository extends JpaRepository<ModeracionMultimedia, Long> {
List<ModeracionMultimedia> findByNombreContaining(String nombre);
}