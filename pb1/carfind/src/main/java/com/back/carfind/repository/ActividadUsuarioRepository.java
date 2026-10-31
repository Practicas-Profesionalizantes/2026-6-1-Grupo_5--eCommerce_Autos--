package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.ActividadUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ActividadUsuarioRepository extends JpaRepository<ActividadUsuario, Long> {
List<ActividadUsuario> findByNombreContaining(String nombre);
}