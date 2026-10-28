package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Transmisiones;
import com.back.carfind.service.TransmisionesService;

@RestController
@RequestMapping("/api/transmisiones")
@CrossOrigin(origins = "*")
public class TransmisionesController {

    private final TransmisionesService service;

    public TransmisionesController(TransmisionesService service) {
        this.service = service;
    }

    @PostMapping
    public Transmisiones crearTransmision(@RequestBody Transmisiones transmision) {
        return service.crearTransmision(transmision);
    }

    @GetMapping("/{id}")
    public Transmisiones obtenerTransmision(@PathVariable Long id) {
        return service.obtenerTransmision(id);
    }

    @GetMapping
    public List<Transmisiones> listarTransmisiones() {
        return service.listarTransmisiones();
    }

    @PutMapping("/{id}")
    public Transmisiones actualizarTransmision(@PathVariable Long id, @RequestBody Transmisiones transmision) {
        return service.actualizarTransmision(id, transmision);
    }

    @DeleteMapping("/{id}")
    public void eliminarTransmision(@PathVariable Long id) {
        service.eliminarTransmision(id);
    }
}
