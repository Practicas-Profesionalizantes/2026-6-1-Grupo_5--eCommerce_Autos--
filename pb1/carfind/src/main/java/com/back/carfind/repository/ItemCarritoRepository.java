package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.ItemCarrito;
import com.back.carfind.model.ItemCarritoId;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, ItemCarritoId> {
}
