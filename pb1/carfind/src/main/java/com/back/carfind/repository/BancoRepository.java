package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Banco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
List<Banco> findByNombreContaining(String nombre);
} 