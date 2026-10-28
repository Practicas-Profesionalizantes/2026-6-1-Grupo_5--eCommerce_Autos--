package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Sesiones;
import com.back.carfind.service.SesionesService;

@RestController
@RequestMapping("/api/sesiones")
@CrossOrigin(origins = "*")
public class SesionesController {

    private final SesionesService service;

    public SesionesController(SesionesService service) {
        this.service = service;
    }

    @PostMapping
    public Sesiones crearSesion(@RequestBody Sesiones sesion) {
        return service.crearSesion(sesion);
    }

    @GetMapping("/{id}")
    public Sesiones obtenerSesion(@PathVariable Long id) {
        return service.obtenerSesion(id);
    }

    @GetMapping
    public List<Sesiones> listarSesiones() {
        return service.listarSesiones();
    }

    @PutMapping("/{id}")
    public Sesiones actualizarSesion(@PathVariable Long id, @RequestBody Sesiones sesion) {
        return service.actualizarSesion(id, sesion);
    }

    @DeleteMapping("/{id}")
    public void eliminarSesion(@PathVariable Long id) {
        service.eliminarSesion(id);
    }
}
