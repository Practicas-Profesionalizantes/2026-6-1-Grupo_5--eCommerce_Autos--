package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Modelos;
import com.back.carfind.service.ModelosService;

@RestController
@RequestMapping("/api/modelos")
@CrossOrigin(origins = "*")
public class ModelosController {

    private final ModelosService service;

    public ModelosController(ModelosService service) {
        this.service = service;
    }

    @PostMapping
    public Modelos crearModelo(@RequestBody Modelos modelo) {
        return service.crearModelo(modelo);
    }

    @GetMapping("/{id}")
    public Modelos obtenerModelo(@PathVariable Long id) {
        return service.obtenerModelo(id);
    }

    @GetMapping
    public List<Modelos> listarModelos() {
        return service.listarModelos();
    }

    @PutMapping("/{id}")
    public Modelos actualizarModelo(@PathVariable Long id, @RequestBody Modelos modelo) {
        return service.actualizarModelo(id, modelo);
    }

    @DeleteMapping("/{id}")
    public void eliminarModelo(@PathVariable Long id) {
        service.eliminarModelo(id);
    }
}
