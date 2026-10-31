package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Chat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
List<Chat> findByNombreContaining(String nombre);
} 