package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.ActividadUsuario;
import com.back.carfind.repository.ActividadUsuarioRepository;

@Service
public class ActividadUsuarioService {

    @Autowired
    private ActividadUsuarioRepository repository;

    public ActividadUsuario crearActividadUsuario(ActividadUsuario actividadUsuario) {
        return repository.save(actividadUsuario);
    }

    public ActividadUsuario obtenerActividadUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad de usuario no encontrada"));
    }

    public List<ActividadUsuario> listarActividadesUsuario() {
        return repository.findAll();
    }

    public ActividadUsuario actualizarActividadUsuario(Long id, ActividadUsuario datos) {
        ActividadUsuario actividadUsuario = obtenerActividadUsuario(id);
        actividadUsuario.setUsuarioId(datos.getUsuarioId());
        actividadUsuario.setAccion(datos.getAccion());
        actividadUsuario.setDescripcion(datos.getDescripcion());
        actividadUsuario.setFecha(datos.getFecha());
        return repository.save(actividadUsuario);
    }

    public void eliminarActividadUsuario(Long id) {
        ActividadUsuario actividadUsuario = obtenerActividadUsuario(id);
        repository.delete(actividadUsuario);
    }
}
