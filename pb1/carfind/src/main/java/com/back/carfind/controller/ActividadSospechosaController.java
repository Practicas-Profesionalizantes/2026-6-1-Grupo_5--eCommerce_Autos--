package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.ActividadSospechosa;
import com.back.carfind.service.ActividadSospechosaService;

@RestController
@RequestMapping("/api/actividades-sospechosas")
@CrossOrigin(origins = "*")
public class ActividadSospechosaController {

    private final ActividadSospechosaService service;

    public ActividadSospechosaController(ActividadSospechosaService service) {
        this.service = service;
    }

    @PostMapping
    public ActividadSospechosa crearActividadSospechosa(@RequestBody ActividadSospechosa actividadSospechosa) {
        return service.crearActividadSospechosa(actividadSospechosa);
    }

    @GetMapping("/{id}")
    public ActividadSospechosa obtenerActividadSospechosa(@PathVariable Long id) {
        return service.obtenerActividadSospechosa(id);
    }

    @GetMapping
    public List<ActividadSospechosa> listarActividadesSospechosas() {
        return service.listarActividadesSospechosas();
    }

    @PutMapping("/{id}")
    public ActividadSospechosa actualizarActividadSospechosa(@PathVariable Long id, @RequestBody ActividadSospechosa actividadSospechosa) {
        return service.actualizarActividadSospechosa(id, actividadSospechosa);
    }

    @DeleteMapping("/{id}")
    public void eliminarActividadSospechosa(@PathVariable Long id) {
        service.eliminarActividadSospechosa(id);
    }
}
