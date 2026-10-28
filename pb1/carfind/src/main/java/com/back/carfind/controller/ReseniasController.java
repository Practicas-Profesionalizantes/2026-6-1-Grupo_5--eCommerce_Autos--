package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Resenias;
import com.back.carfind.service.ReseniasService;

@RestController
@RequestMapping("/api/resenias")
@CrossOrigin(origins = "*")
public class ReseniasController {

    private final ReseniasService service;

    public ReseniasController(ReseniasService service) {
        this.service = service;
    }

    @PostMapping
    public Resenias crearResenia(@RequestBody Resenias resenia) {
        return service.crearResenia(resenia);
    }

    @GetMapping("/{id}")
    public Resenias obtenerResenia(@PathVariable Long id) {
        return service.obtenerResenia(id);
    }

    @GetMapping
    public List<Resenias> listarResenias() {
        return service.listarResenias();
    }

    @PutMapping("/{id}")
    public Resenias actualizarResenia(@PathVariable Long id, @RequestBody Resenias resenia) {
        return service.actualizarResenia(id, resenia);
    }

    @DeleteMapping("/{id}")
    public void eliminarResenia(@PathVariable Long id) {
        service.eliminarResenia(id);
    }
}
