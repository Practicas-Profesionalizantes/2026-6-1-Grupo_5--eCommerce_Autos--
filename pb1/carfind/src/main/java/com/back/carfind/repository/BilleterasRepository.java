package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Billeteras;

@Repository
public interface BilleterasRepository extends JpaRepository<Billeteras, Long> {
}
