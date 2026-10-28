package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Reembolsos;
import com.back.carfind.service.ReembolsosService;

@RestController
@RequestMapping("/api/reembolsos")
@CrossOrigin(origins = "*")
public class ReembolsosController {

    private final ReembolsosService service;

    public ReembolsosController(ReembolsosService service) {
        this.service = service;
    }

    @PostMapping
    public Reembolsos crearReembolso(@RequestBody Reembolsos reembolso) {
        return service.crearReembolso(reembolso);
    }

    @GetMapping("/{id}")
    public Reembolsos obtenerReembolso(@PathVariable Long id) {
        return service.obtenerReembolso(id);
    }

    @GetMapping
    public List<Reembolsos> listarReembolsos() {
        return service.listarReembolsos();
    }

    @PutMapping("/{id}")
    public Reembolsos actualizarReembolso(@PathVariable Long id, @RequestBody Reembolsos reembolso) {
        return service.actualizarReembolso(id, reembolso);
    }

    @DeleteMapping("/{id}")
    public void eliminarReembolso(@PathVariable Long id) {
        service.eliminarReembolso(id);
    }
}
