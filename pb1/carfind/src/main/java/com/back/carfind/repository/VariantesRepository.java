package com.back.carfind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Variantes;

@Repository
public interface VariantesRepository extends JpaRepository<Variantes, Long> {

    List<Variantes> findByNombreContaining(String nombre);
}
