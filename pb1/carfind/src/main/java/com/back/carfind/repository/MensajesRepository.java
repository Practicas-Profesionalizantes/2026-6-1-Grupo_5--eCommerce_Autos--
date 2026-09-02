package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Mensajes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MensajesRepository extends JpaRepository<Mensajes, Long> {
List<Mensajes> findByNombreContaining(String nombre);
}