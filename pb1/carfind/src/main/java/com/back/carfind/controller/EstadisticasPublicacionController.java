package com.back.carfind.controller;

import com.back.carfind.model.EstadisticasPublicacion;
import com.back.carfind.service.EstadisticasPublicacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadisticas-publicacion")
@CrossOrigin(origins = "*")
public class EstadisticasPublicacionController {

    private final EstadisticasPublicacionService service;

    public EstadisticasPublicacionController(EstadisticasPublicacionService service) {
        this.service = service;
    }

    @PostMapping
    public EstadisticasPublicacion crearEstadistica(@RequestBody EstadisticasPublicacion estadisticasPublicacion) {
        return service.crearEstadisticas(estadisticasPublicacion);
    }

    @GetMapping("/{id}")
    public EstadisticasPublicacion obtenerEstadistica(@PathVariable Long id) {
        return service.obtenerEstadisticas(id);
    }

    @GetMapping
    public List<EstadisticasPublicacion> listarEstadisticas() {
        return service.listarEstadisticas();
    }

    @PutMapping("/{id}")
    public EstadisticasPublicacion actualizarEstadistica(@PathVariable Long id, @RequestBody EstadisticasPublicacion estadisticasPublicacion) {
        return service.actualizarEstadisticas(id, estadisticasPublicacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstadistica(@PathVariable Long id) {
        service.eliminarEstadisticas(id);
    }
}
