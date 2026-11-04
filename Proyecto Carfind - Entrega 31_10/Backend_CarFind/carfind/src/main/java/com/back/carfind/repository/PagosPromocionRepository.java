package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.PagosPromocion;

@Repository
public interface PagosPromocionRepository extends JpaRepository<PagosPromocion, Long> {
}
