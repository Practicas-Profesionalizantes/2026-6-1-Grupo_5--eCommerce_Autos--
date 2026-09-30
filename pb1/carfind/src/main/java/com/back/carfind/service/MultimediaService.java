package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Multimedia;
import com.back.carfind.repository.MultimediaRepository;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository repository;

    public Multimedia crearMultimedia(Multimedia multimedia) {
        return repository.save(multimedia);
    }

    public Multimedia obtenerMultimedia(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Multimedia no encontrada"
                ));
    }

    public List<Multimedia> listarMultimedia() {
        return repository.findAll();
    }

    public Multimedia actualizarMultimedia(
            Long id,
            Multimedia datos) {

        Multimedia multimedia = obtenerMultimedia(id);

        multimedia.setId_publicacion(
                datos.getId_publicacion()
        );

        multimedia.setUrl(datos.getUrl());
        multimedia.setTipo(datos.getTipo());
        multimedia.setOrden(datos.getOrden());
        multimedia.setFecha_creacion(
                datos.getFecha_creacion()
        );

        return repository.save(multimedia);
    }

    public void eliminarMultimedia(Long id) {
        Multimedia multimedia = obtenerMultimedia(id);

        repository.delete(multimedia);
    }
}