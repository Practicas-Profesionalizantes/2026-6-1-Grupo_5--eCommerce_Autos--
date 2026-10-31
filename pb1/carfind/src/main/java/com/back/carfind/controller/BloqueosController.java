package com.back.carfind.controller;

import com.back.carfind.model.Bloqueos;
import com.back.carfind.service.BloqueosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloqueos")
@CrossOrigin(origins = "*")
public class BloqueosController {

    private final BloqueosService service;

    public BloqueosController(BloqueosService service) {
        this.service = service;
    }

    @PostMapping
    public Bloqueos crearBloqueo(@RequestBody Bloqueos bloqueo) {
        return service.crearBloqueos(bloqueo);
    }

    @GetMapping("/{id}")
    public Bloqueos obtenerBloqueo(@PathVariable Long id) {
        return service.obtenerBloqueos(id);
    }

    @GetMapping
    public List<Bloqueos> listarBloqueos() {
        return service.listarBloqueos();
    }

    @PutMapping("/{id}")
    public Bloqueos actualizarBloqueo(@PathVariable Long id, @RequestBody Bloqueos bloqueo) {
        return service.actualizarBloqueos(id, bloqueo);
    }

    @DeleteMapping("/{id}")
    public void eliminarBloqueo(@PathVariable Long id) {
        service.eliminarbloqueos(id);
    }
}
