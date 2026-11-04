package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Favoritos;
import com.back.carfind.model.FavoritosId;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, FavoritosId> {
}
