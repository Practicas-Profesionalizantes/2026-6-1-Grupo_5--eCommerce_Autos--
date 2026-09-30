package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.HistorialPublicaciones;
import com.back.carfind.repository.HistorialPublicacionesRepository;

@Service
public class HistorialPublicacionesService {

    @Autowired
    private HistorialPublicacionesRepository repository;

    public HistorialPublicaciones crearHistorialPublicacion(
            HistorialPublicaciones historialPublicacion) {

        return repository.save(historialPublicacion);
    }

    public HistorialPublicaciones obtenerHistorialPublicacion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Historial de publicación no encontrado"
                ));
    }

    public List<HistorialPublicaciones> listarHistorialPublicaciones() {
        return repository.findAll();
    }

    public HistorialPublicaciones actualizarHistorialPublicacion(
            Long id,
            HistorialPublicaciones datos) {

        HistorialPublicaciones historialPublicacion =
                obtenerHistorialPublicacion(id);

        historialPublicacion.setId_usuario(
                datos.getId_usuario()
        );

        historialPublicacion.setEstado(
                datos.getEstado()
        );

        historialPublicacion.setFecha_publicacion(
                datos.getFecha_publicacion()
        );

        return repository.save(historialPublicacion);
    }

    public void eliminarHistorialPublicacion(Long id) {
        HistorialPublicaciones historialPublicacion =
                obtenerHistorialPublicacion(id);

        repository.delete(historialPublicacion);
    }
}