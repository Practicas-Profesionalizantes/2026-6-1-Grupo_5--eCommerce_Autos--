package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Notificaciones;
import com.back.carfind.service.NotificacionesService;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionesController {

    private final NotificacionesService service;

    public NotificacionesController(NotificacionesService service) {
        this.service = service;
    }

    @PostMapping
    public Notificaciones crearNotificacion(@RequestBody Notificaciones notificacion) {
        return service.crearNotificacion(notificacion);
    }

    @GetMapping("/{id}")
    public Notificaciones obtenerNotificacion(@PathVariable Long id) {
        return service.obtenerNotificacion(id);
    }

    @GetMapping
    public List<Notificaciones> listarNotificaciones() {
        return service.listarNotificaciones();
    }

    @PutMapping("/{id}")
    public Notificaciones actualizarNotificacion(@PathVariable Long id, @RequestBody Notificaciones notificacion) {
        return service.actualizarNotificacion(id, notificacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarNotificacion(@PathVariable Long id) {
        service.eliminarNotificacion(id);
    }
}
