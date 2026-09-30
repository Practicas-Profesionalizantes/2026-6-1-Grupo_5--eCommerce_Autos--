package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.VistasPublicacion;
import com.back.carfind.repository.VistasPublicacionRepository;

@Service
public class VistasPublicacionService {

    @Autowired
    private VistasPublicacionRepository repository;

    public VistasPublicacion crearVista(
            VistasPublicacion vista) {

        return repository.save(vista);
    }

    public VistasPublicacion obtenerVista(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Vista de publicación no encontrada"
                ));
    }

    public List<VistasPublicacion> listarVistas() {
        return repository.findAll();
    }

    public VistasPublicacion actualizarVista(
            Long id,
            VistasPublicacion datos) {

        VistasPublicacion vista = obtenerVista(id);

        vista.setId_usuario(datos.getId_usuario());
        vista.setId_publicacion(
                datos.getId_publicacion()
        );
        vista.setFecha_vista(
                datos.getFecha_vista()
        );

        return repository.save(vista);
    }

    public void eliminarVista(Long id) {
        VistasPublicacion vista = obtenerVista(id);

        repository.delete(vista);
    }
}