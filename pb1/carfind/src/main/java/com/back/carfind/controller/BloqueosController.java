package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Bloqueos;
import com.back.carfind.service.BloqueosService;

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
        return service.crearBloqueo(bloqueo);
    }

    @GetMapping("/{usuarioId}/{usuarioBloqueadoId}")
    public Bloqueos obtenerBloqueo(@PathVariable Long usuarioId, @PathVariable Long usuarioBloqueadoId) {
        return service.obtenerBloqueo(usuarioId, usuarioBloqueadoId);
    }

    @GetMapping
    public List<Bloqueos> listarBloqueos() {
        return service.listarBloqueos();
    }

    @DeleteMapping("/{usuarioId}/{usuarioBloqueadoId}")
    public void eliminarBloqueo(@PathVariable Long usuarioId, @PathVariable Long usuarioBloqueadoId) {
        service.eliminarBloqueo(usuarioId, usuarioBloqueadoId);
    }
}
