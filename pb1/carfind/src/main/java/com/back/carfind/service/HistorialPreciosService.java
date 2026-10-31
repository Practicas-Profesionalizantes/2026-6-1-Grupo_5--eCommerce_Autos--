package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.HistorialPrecios;
import com.back.carfind.repository.HistorialPreciosRepository;

@Service
public class HistorialPreciosService {

    @Autowired
    private HistorialPreciosRepository repository;

    public HistorialPrecios crearHistorialPrecio(
            HistorialPrecios historialPrecio) {

        return repository.save(historialPrecio);
    }

    public HistorialPrecios obtenerHistorialPrecio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Historial de precio no encontrado"
                ));
    }

    public List<HistorialPrecios> listarHistorialPrecios() {
        return repository.findAll();
    }

    public HistorialPrecios actualizarHistorialPrecio(
            Long id,
            HistorialPrecios datos) {

        HistorialPrecios historialPrecio =
                obtenerHistorialPrecio(id);

        historialPrecio.setId_publicacion(
                datos.getId_publicacion()
        );

        historialPrecio.setPrecio(
                datos.getPrecio()
        );

        historialPrecio.setFecha_precio(
                datos.getFecha_precio()
        );

        return repository.save(historialPrecio);
    }

    public void eliminarHistorialPrecio(Long id) {
        HistorialPrecios historialPrecio =
                obtenerHistorialPrecio(id);

        repository.delete(historialPrecio);
    }
}