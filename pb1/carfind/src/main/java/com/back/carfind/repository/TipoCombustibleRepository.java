package com.back.carfind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.TipoCombustible;

@Repository
public interface TipoCombustibleRepository extends JpaRepository<TipoCombustible, Long> {

    List<TipoCombustible> findByNombreContaining(String nombre);
}
