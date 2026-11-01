package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Compras;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Long> {
}
