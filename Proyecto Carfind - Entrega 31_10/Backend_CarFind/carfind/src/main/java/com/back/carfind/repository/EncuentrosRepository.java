package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Encuentros;

@Repository
public interface EncuentrosRepository extends JpaRepository<Encuentros, Long> {
}
