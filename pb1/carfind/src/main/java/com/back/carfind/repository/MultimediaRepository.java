package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Multimedia;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
}
