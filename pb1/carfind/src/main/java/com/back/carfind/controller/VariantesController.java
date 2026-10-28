package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Variantes;
import com.back.carfind.service.VariantesService;

@RestController
@RequestMapping("/api/variantes")
@CrossOrigin(origins = "*")
public class VariantesController {

    private final VariantesService service;

    public VariantesController(VariantesService service) {
        this.service = service;
    }

    @PostMapping
    public Variantes crearVariante(@RequestBody Variantes variante) {
        return service.crearVariante(variante);
    }

    @GetMapping("/{id}")
    public Variantes obtenerVariante(@PathVariable Long id) {
        return service.obtenerVariante(id);
    }

    @GetMapping
    public List<Variantes> listarVariantes() {
        return service.listarVariantes();
    }

    @PutMapping("/{id}")
    public Variantes actualizarVariante(@PathVariable Long id, @RequestBody Variantes variante) {
        return service.actualizarVariante(id, variante);
    }

    @DeleteMapping("/{id}")
    public void eliminarVariante(@PathVariable Long id) {
        service.eliminarVariante(id);
    }
}
