package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Billeteras;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BilleterasRepository extends JpaRepository<Billeteras, Long> {
List<Billeteras> findByNombreContaining(String nombre);
} 