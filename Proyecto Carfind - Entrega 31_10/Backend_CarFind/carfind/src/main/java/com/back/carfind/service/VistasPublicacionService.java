package com.back.carfind.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.VistasPublicacion;
import com.back.carfind.model.VistasPublicacionId;
import com.back.carfind.repository.VistasPublicacionRepository;

@Service
public class VistasPublicacionService {

    @Autowired
    private VistasPublicacionRepository repository;

    public VistasPublicacion crearVistaPublicacion(VistasPublicacion vistaPublicacion) {
        return repository.save(vistaPublicacion);
    }

    public VistasPublicacion obtenerVistaPublicacion(Long usuarioId, Long publicacionId, LocalDateTime fecha) {
        return repository.findById(new VistasPublicacionId(usuarioId, publicacionId, fecha))
                .orElseThrow(() -> new RuntimeException("Vista de publicacion no encontrada"));
    }

    public List<VistasPublicacion> listarVistasPublicacion() {
        return repository.findAll();
    }

    public void eliminarVistaPublicacion(Long usuarioId, Long publicacionId, LocalDateTime fecha) {
        VistasPublicacion vistaPublicacion = obtenerVistaPublicacion(usuarioId, publicacionId, fecha);
        repository.delete(vistaPublicacion);
    }
}
