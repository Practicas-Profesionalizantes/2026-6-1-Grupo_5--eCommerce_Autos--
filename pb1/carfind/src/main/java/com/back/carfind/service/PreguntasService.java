package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Preguntas;
import com.back.carfind.repository.PreguntasRepository;

@Service
public class PreguntasService {

    @Autowired
    private PreguntasRepository repository;

    public Preguntas crearPregunta(Preguntas pregunta) {
        return repository.save(pregunta);
    }

    public Preguntas obtenerPregunta(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Pregunta no encontrada"
                ));
    }

    public List<Preguntas> listarPreguntas() {
        return repository.findAll();
    }

    public Preguntas actualizarPregunta(
            Long id,
            Preguntas datos) {

        Preguntas pregunta = obtenerPregunta(id);

        pregunta.setId_publicacion(
                datos.getId_publicacion()
        );

        pregunta.setId_usaurio(
                datos.getId_usaurio()
        );

        pregunta.setDescripcion(
                datos.getDescripcion()
        );

        pregunta.setFecha_pregunta(
                datos.getFecha_pregunta()
        );

        return repository.save(pregunta);
    }

    public void eliminarPregunta(Long id) {
        Preguntas pregunta = obtenerPregunta(id);

        repository.delete(pregunta);
    }
}