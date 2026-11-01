package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Notificaciones;
import com.back.carfind.repository.NotificacionesRepository;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository repository;

    public Notificaciones crearNotificacion(Notificaciones notificacion) {
        return repository.save(notificacion);
    }

    public Notificaciones obtenerNotificacion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
    }

    public List<Notificaciones> listarNotificaciones() {
        return repository.findAll();
    }

    public Notificaciones actualizarNotificacion(Long id, Notificaciones datos) {
        Notificaciones notificacion = obtenerNotificacion(id);
        notificacion.setUsuarioId(datos.getUsuarioId());
        notificacion.setTipo(datos.getTipo());
        notificacion.setMensaje(datos.getMensaje());
        notificacion.setLeido(datos.getLeido());
        notificacion.setEntidadId(datos.getEntidadId());
        notificacion.setFecha(datos.getFecha());
        return repository.save(notificacion);
    }

    public void eliminarNotificacion(Long id) {
        Notificaciones notificacion = obtenerNotificacion(id);
        repository.delete(notificacion);
    }
}
