package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Marcas;
import com.back.carfind.service.MarcasService;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*")
public class MarcasController {

    private final MarcasService service;

    public MarcasController(MarcasService service) {
        this.service = service;
    }

    @PostMapping
    public Marcas crearMarca(@RequestBody Marcas marca) {
        return service.crearMarca(marca);
    }

    @GetMapping("/{id}")
    public Marcas obtenerMarca(@PathVariable Long id) {
        return service.obtenerMarca(id);
    }

    @GetMapping
    public List<Marcas> listarMarcas() {
        return service.listarMarcas();
    }

    @PutMapping("/{id}")
    public Marcas actualizarMarca(@PathVariable Long id, @RequestBody Marcas marca) {
        return service.actualizarMarca(id, marca);
    }

    @DeleteMapping("/{id}")
    public void eliminarMarca(@PathVariable Long id) {
        service.eliminarMarca(id);
    }
}
