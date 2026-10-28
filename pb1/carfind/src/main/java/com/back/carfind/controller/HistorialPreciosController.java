package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.HistorialPrecios;
import com.back.carfind.service.HistorialPreciosService;

@RestController
@RequestMapping("/api/historial-precios")
@CrossOrigin(origins = "*")
public class HistorialPreciosController {

    private final HistorialPreciosService service;

    public HistorialPreciosController(HistorialPreciosService service) {
        this.service = service;
    }

    @PostMapping
    public HistorialPrecios crearHistorialPrecio(@RequestBody HistorialPrecios historialPrecio) {
        return service.crearHistorialPrecio(historialPrecio);
    }

    @GetMapping("/{id}")
    public HistorialPrecios obtenerHistorialPrecio(@PathVariable Long id) {
        return service.obtenerHistorialPrecio(id);
    }

    @GetMapping
    public List<HistorialPrecios> listarHistorialPrecios() {
        return service.listarHistorialPrecios();
    }

    @PutMapping("/{id}")
    public HistorialPrecios actualizarHistorialPrecio(@PathVariable Long id, @RequestBody HistorialPrecios historialPrecio) {
        return service.actualizarHistorialPrecio(id, historialPrecio);
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorialPrecio(@PathVariable Long id) {
        service.eliminarHistorialPrecio(id);
    }
}
