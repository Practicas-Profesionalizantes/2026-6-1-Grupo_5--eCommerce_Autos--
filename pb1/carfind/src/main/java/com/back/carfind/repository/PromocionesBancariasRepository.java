package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.PromocionesBancarias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PromocionesBancariasRepository extends JpaRepository<PromocionesBancarias, Long> {
List<PromocionesBancarias> findByNombreContaining(String nombre);
}