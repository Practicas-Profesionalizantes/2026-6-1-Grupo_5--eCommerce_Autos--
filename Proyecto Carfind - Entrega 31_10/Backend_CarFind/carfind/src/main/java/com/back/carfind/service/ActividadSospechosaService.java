package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.ActividadSospechosa;
import com.back.carfind.repository.ActividadSospechosaRepository;

@Service
public class ActividadSospechosaService {

    @Autowired
    private ActividadSospechosaRepository repository;

    public ActividadSospechosa crearActividadSospechosa(ActividadSospechosa actividadSospechosa) {
        return repository.save(actividadSospechosa);
    }

    public ActividadSospechosa obtenerActividadSospechosa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad sospechosa no encontrada"));
    }

    public List<ActividadSospechosa> listarActividadesSospechosas() {
        return repository.findAll();
    }

    public ActividadSospechosa actualizarActividadSospechosa(Long id, ActividadSospechosa datos) {
        ActividadSospechosa actividadSospechosa = obtenerActividadSospechosa(id);
        actividadSospechosa.setUsuarioId(datos.getUsuarioId());
        actividadSospechosa.setTipo(datos.getTipo());
        actividadSospechosa.setDescripcion(datos.getDescripcion());
        actividadSospechosa.setFecha(datos.getFecha());
        return repository.save(actividadSospechosa);
    }

    public void eliminarActividadSospechosa(Long id) {
        ActividadSospechosa actividadSospechosa = obtenerActividadSospechosa(id);
        repository.delete(actividadSospechosa);
    }
}
