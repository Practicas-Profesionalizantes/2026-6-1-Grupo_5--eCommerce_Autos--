package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Publicaciones;
import com.back.carfind.repository.PublicacionesRepository;

@Service
public class PublicacionesService {

    @Autowired
    private PublicacionesRepository repository;

    public Publicaciones crearPublicacion(Publicaciones publicacion) {
        return repository.save(publicacion);
    }

    public Publicaciones obtenerPublicacion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));
    }

    public List<Publicaciones> listarPublicaciones() {
        return repository.findAll();
    }

    public Publicaciones actualizarPublicacion(Long id, Publicaciones datos) {
        Publicaciones publicacion = obtenerPublicacion(id);
        publicacion.setUsuarioId(datos.getUsuarioId());
        publicacion.setAutoId(datos.getAutoId());
        publicacion.setPrecio(datos.getPrecio());
        publicacion.setMoneda(datos.getMoneda());
        publicacion.setEstado(datos.getEstado());
        publicacion.setScore(datos.getScore());
        publicacion.setDestacado(datos.getDestacado());
        publicacion.setVisitas(datos.getVisitas());
        publicacion.setDescripcion(datos.getDescripcion());
        publicacion.setFechaPublicacion(datos.getFechaPublicacion());
        publicacion.setFechaExpiracion(datos.getFechaExpiracion());
        publicacion.setEliminado(datos.getEliminado());
        publicacion.setFechaEliminacion(datos.getFechaEliminacion());
        return repository.save(publicacion);
    }

    public void eliminarPublicacion(Long id) {
        Publicaciones publicacion = obtenerPublicacion(id);
        repository.delete(publicacion);
    }
}
