package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Pagos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PagosRepository extends JpaRepository<Pagos, Long> {
List<Pagos> findByNombreContaining(String nombre);
}