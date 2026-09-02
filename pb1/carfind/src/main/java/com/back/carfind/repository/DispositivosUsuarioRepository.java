package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.DispositivosUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface DispositivosUsuarioRepository extends JpaRepository<DispositivosUsuario, Long> {
List<DispositivosUsuario> findByNombreContaining(String nombre);
} 