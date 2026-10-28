package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.InformeDominio;

@Repository
public interface InformeDominioRepository extends JpaRepository<InformeDominio, Long> {
}
