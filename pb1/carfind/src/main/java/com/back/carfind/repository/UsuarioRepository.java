package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
List<Usuario> findByNombreContaining(String nombre);
}