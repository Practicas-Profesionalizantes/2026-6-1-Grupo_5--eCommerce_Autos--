package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Resenias;

@Repository
public interface ReseniasRepository extends JpaRepository<Resenias, Long> {
}
