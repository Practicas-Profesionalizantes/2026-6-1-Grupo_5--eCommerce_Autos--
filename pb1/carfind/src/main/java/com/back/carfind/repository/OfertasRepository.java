package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Ofertas;

@Repository
public interface OfertasRepository extends JpaRepository<Ofertas, Long> {
}
