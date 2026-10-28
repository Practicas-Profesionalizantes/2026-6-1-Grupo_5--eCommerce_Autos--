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

    public EstadisticasPublicacion crearEstadistica(EstadisticasPublicacion estadistica) {
        return repository.save(estadistica);
    }

    public EstadisticasPublicacion obtenerEstadistica(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadisticas de publicacion no encontradas"));
    }

    public List<EstadisticasPublicacion> listarEstadisticas() {
        return repository.findAll();
    }

    public EstadisticasPublicacion actualizarEstadistica(Long id, EstadisticasPublicacion datos) {
        EstadisticasPublicacion estadistica = obtenerEstadistica(id);
        estadistica.setVistas(datos.getVistas());
        estadistica.setClicks(datos.getClicks());
        estadistica.setFavoritos(datos.getFavoritos());
        estadistica.setContactos(datos.getContactos());
        estadistica.setCompartidos(datos.getCompartidos());
        return repository.save(estadistica);
    }

    public void eliminarEstadistica(Long id) {
        EstadisticasPublicacion estadistica = obtenerEstadistica(id);
        repository.delete(estadistica);
    }
}
