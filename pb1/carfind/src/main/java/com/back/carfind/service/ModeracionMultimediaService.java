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

    public ModeracionMultimedia crearModeracionMultimedia(ModeracionMultimedia moderacionMultimedia) {
        return repository.save(moderacionMultimedia);
    }

    public ModeracionMultimedia obtenerModeracionMultimedia(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moderacion no encontrada"));
    }

    public List<ModeracionMultimedia> listarModeracionesMultimedia() {
        return repository.findAll();
    }

    public ModeracionMultimedia actualizarModeracionMultimedia(Long id, ModeracionMultimedia datos) {
        ModeracionMultimedia moderacionMultimedia = obtenerModeracionMultimedia(id);
        moderacionMultimedia.setMultimediaId(datos.getMultimediaId());
        moderacionMultimedia.setEstado(datos.getEstado());
        moderacionMultimedia.setMotivo(datos.getMotivo());
        moderacionMultimedia.setFecha(datos.getFecha());
        return repository.save(moderacionMultimedia);
    }

    public void eliminarModeracionMultimedia(Long id) {
        ModeracionMultimedia moderacionMultimedia = obtenerModeracionMultimedia(id);
        repository.delete(moderacionMultimedia);
    }
}
