package com.back.carfind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.carfind.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
