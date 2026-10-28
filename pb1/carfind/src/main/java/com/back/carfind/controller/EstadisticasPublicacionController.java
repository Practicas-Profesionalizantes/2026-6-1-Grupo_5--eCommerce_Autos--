package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.EstadisticasPublicacion;
import com.back.carfind.service.EstadisticasPublicacionService;

@RestController
@RequestMapping("/api/estadisticas-publicacion")
@CrossOrigin(origins = "*")
public class EstadisticasPublicacionController {

    private final EstadisticasPublicacionService service;

    public EstadisticasPublicacionController(EstadisticasPublicacionService service) {
        this.service = service;
    }

    @PostMapping
    public EstadisticasPublicacion crearEstadistica(@RequestBody EstadisticasPublicacion estadistica) {
        return service.crearEstadistica(estadistica);
    }

    @GetMapping("/{id}")
    public EstadisticasPublicacion obtenerEstadistica(@PathVariable Long id) {
        return service.obtenerEstadistica(id);
    }

    @GetMapping
    public List<EstadisticasPublicacion> listarEstadisticas() {
        return service.listarEstadisticas();
    }

    @PutMapping("/{id}")
    public EstadisticasPublicacion actualizarEstadistica(@PathVariable Long id, @RequestBody EstadisticasPublicacion estadistica) {
        return service.actualizarEstadistica(id, estadistica);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstadistica(@PathVariable Long id) {
        service.eliminarEstadistica(id);
    }
}
