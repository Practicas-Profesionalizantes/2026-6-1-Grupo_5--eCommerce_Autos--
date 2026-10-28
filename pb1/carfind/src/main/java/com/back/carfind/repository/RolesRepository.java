package com.back.carfind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    List<Roles> findByNombreContaining(String nombre);
}
