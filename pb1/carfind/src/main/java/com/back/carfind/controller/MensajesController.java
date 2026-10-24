package com.back.carfind.controller;

import com.back.carfind.model.Mensajes;
import com.back.carfind.service.MensajesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajesController {

    private final MensajesService service;

    public MensajesController(MensajesService service) {
        this.service = service;
    }

    @PostMapping
    public Mensajes crearMensaje(@RequestBody Mensajes mensaje) {
        return service.crearMensaje(mensaje);
    }

    @GetMapping("/{id}")
    public Mensajes obtenerMensaje(@PathVariable Long id) {
        return service.obtenerMensaje(id);
    }

    @GetMapping
    public List<Mensajes> listarMensajes() {
        return service.listarMensajes();
    }

    @PutMapping("/{id}")
    public Mensajes actualizarMensaje(@PathVariable Long id, @RequestBody Mensajes mensaje) {
        return service.actualizarMensaje(id, mensaje);
    }

    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable Long id) {
        service.eliminarMensaje(id);
    }
}
