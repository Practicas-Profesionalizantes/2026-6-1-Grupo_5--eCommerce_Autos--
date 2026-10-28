package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.HistorialPublicaciones;
import com.back.carfind.service.HistorialPublicacionesService;

@RestController
@RequestMapping("/api/historial-publicaciones")
@CrossOrigin(origins = "*")
public class HistorialPublicacionesController {

    private final HistorialPublicacionesService service;

    public HistorialPublicacionesController(HistorialPublicacionesService service) {
        this.service = service;
    }

    @PostMapping
    public HistorialPublicaciones crearHistorialPublicacion(@RequestBody HistorialPublicaciones historialPublicacion) {
        return service.crearHistorialPublicacion(historialPublicacion);
    }

    @GetMapping("/{id}")
    public HistorialPublicaciones obtenerHistorialPublicacion(@PathVariable Long id) {
        return service.obtenerHistorialPublicacion(id);
    }

    @GetMapping
    public List<HistorialPublicaciones> listarHistorialPublicaciones() {
        return service.listarHistorialPublicaciones();
    }

    @PutMapping("/{id}")
    public HistorialPublicaciones actualizarHistorialPublicacion(@PathVariable Long id, @RequestBody HistorialPublicaciones historialPublicacion) {
        return service.actualizarHistorialPublicacion(id, historialPublicacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorialPublicacion(@PathVariable Long id) {
        service.eliminarHistorialPublicacion(id);
    }
}
