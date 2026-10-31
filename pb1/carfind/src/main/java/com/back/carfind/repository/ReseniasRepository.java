package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Resenias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ReseniasRepository extends JpaRepository<Resenias, Long> {
List<Resenias> findByNombreContaining(String nombre);
}