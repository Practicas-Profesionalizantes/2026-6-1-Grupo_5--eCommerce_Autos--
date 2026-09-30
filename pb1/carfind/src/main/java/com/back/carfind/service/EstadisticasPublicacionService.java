package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.EstadisticasPublicacion;
import com.back.carfind.repository.EstadisticasPublicacionRepository;

@Service
public class EstadisticasPublicacionService {

    @Autowired
    private EstadisticasPublicacionRepository repository;

    public EstadisticasPublicacion crearEstadisticas(
            EstadisticasPublicacion estadisticas) {

        return repository.save(estadisticas);
    }

    public EstadisticasPublicacion obtenerEstadisticas(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Estadísticas de publicación no encontradas"
                ));
    }

    public List<EstadisticasPublicacion> listarEstadisticas() {
        return repository.findAll();
    }

    public EstadisticasPublicacion actualizarEstadisticas(
            Long id,
            EstadisticasPublicacion datos) {

        EstadisticasPublicacion estadisticas =
                obtenerEstadisticas(id);

        estadisticas.setVistas(datos.getVistas());
        estadisticas.setClicks(datos.getClicks());
        estadisticas.setFavoritos(datos.getFavoritos());
        estadisticas.setContactos(datos.getContactos());
        estadisticas.setCompartidos(datos.getCompartidos());

        return repository.save(estadisticas);
    }

    public void eliminarEstadisticas(Long id) {
        EstadisticasPublicacion estadisticas =
                obtenerEstadisticas(id);

        repository.delete(estadisticas);
    }
}