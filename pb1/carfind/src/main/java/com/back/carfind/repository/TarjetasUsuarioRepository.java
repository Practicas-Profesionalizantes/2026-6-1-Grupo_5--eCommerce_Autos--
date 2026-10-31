package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.TarjetasUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TarjetasUsuarioRepository extends JpaRepository<TarjetasUsuario, Long> {
List<TarjetasUsuario> findByNombreContaining(String nombre);
}