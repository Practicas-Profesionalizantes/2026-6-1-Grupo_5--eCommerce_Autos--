package com.back.carfind.repository;
import org.springframework.stereotype.Repository;
import com.back.carfind.model.Multimedia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
List<Multimedia> findByNombreContaining(String nombre);
}