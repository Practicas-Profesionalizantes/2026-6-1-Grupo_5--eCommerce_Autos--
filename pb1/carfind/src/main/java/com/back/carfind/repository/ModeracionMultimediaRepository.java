package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.ModeracionMultimedia;

@Repository
public interface ModeracionMultimediaRepository extends JpaRepository<ModeracionMultimedia, Long> {
}
