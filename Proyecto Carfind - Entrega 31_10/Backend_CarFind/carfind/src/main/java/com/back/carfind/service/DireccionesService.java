package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Direcciones;
import com.back.carfind.repository.DireccionesRepository;

@Service
public class DireccionesService {

    @Autowired
    private DireccionesRepository repository;

    public Direcciones crearDireccion(Direcciones direccion) {
        return repository.save(direccion);
    }

    public Direcciones obtenerDireccion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada"));
    }

    public List<Direcciones> listarDirecciones() {
        return repository.findAll();
    }

    public Direcciones actualizarDireccion(Long id, Direcciones datos) {
        Direcciones direccion = obtenerDireccion(id);
        direccion.setUsuarioId(datos.getUsuarioId());
        direccion.setPais(datos.getPais());
        direccion.setProvincia(datos.getProvincia());
        direccion.setCiudad(datos.getCiudad());
        direccion.setDireccion(datos.getDireccion());
        direccion.setTipo(datos.getTipo());
        direccion.setEsPrincipal(datos.getEsPrincipal());
        direccion.setFechaCreacion(datos.getFechaCreacion());
        return repository.save(direccion);
    }

    public void eliminarDireccion(Long id) {
        Direcciones direccion = obtenerDireccion(id);
        repository.delete(direccion);
    }
}
