package com.back.carfind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.TipoPago;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {

    List<TipoPago> findByNombreContaining(String nombre);
}
