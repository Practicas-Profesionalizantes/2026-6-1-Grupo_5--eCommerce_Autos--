package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Favoritos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Long> {
List<Favoritos> findByNombreContaining(String nombre);
} 