package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.ModeracionMultimedia;
import com.back.carfind.repository.ModeracionMultimediaRepository;

@Service
public class ModeracionMultimediaService {

    @Autowired
    private ModeracionMultimediaRepository repository;

    public ModeracionMultimedia crearModeracion(
            ModeracionMultimedia moderacion) {

        return repository.save(moderacion);
    }

    public ModeracionMultimedia obtenerModeracion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Moderación multimedia no encontrada"
                ));
    }

    public List<ModeracionMultimedia> listarModeraciones() {
        return repository.findAll();
    }

    public ModeracionMultimedia actualizarModeracion(
            Long id,
            ModeracionMultimedia datos) {

        ModeracionMultimedia moderacion =
                obtenerModeracion(id);

        moderacion.setEstado(datos.getEstado());
        moderacion.setMotivo(datos.getMotivo());
        moderacion.setFecha(datos.getFecha());

        return repository.save(moderacion);
    }

    public void eliminarModeracion(Long id) {
        ModeracionMultimedia moderacion =
                obtenerModeracion(id);

        repository.delete(moderacion);
    }
}