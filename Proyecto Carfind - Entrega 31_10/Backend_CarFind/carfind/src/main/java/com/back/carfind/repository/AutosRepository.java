package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Autos;

@Repository
public interface AutosRepository extends JpaRepository<Autos, Long> {
}
