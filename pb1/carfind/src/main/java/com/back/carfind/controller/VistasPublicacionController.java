package com.back.carfind.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.VistasPublicacion;
import com.back.carfind.service.VistasPublicacionService;

@RestController
@RequestMapping("/api/vistas-publicacion")
@CrossOrigin(origins = "*")
public class VistasPublicacionController {

    private final VistasPublicacionService service;

    public VistasPublicacionController(VistasPublicacionService service) {
        this.service = service;
    }

    @PostMapping
    public VistasPublicacion crearVistaPublicacion(@RequestBody VistasPublicacion vistaPublicacion) {
        return service.crearVistaPublicacion(vistaPublicacion);
    }

    @GetMapping("/{usuarioId}/{publicacionId}")
    public VistasPublicacion obtenerVistaPublicacion(@PathVariable Long usuarioId, @PathVariable Long publicacionId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return service.obtenerVistaPublicacion(usuarioId, publicacionId, fecha);
    }

    @GetMapping
    public List<VistasPublicacion> listarVistasPublicacion() {
        return service.listarVistasPublicacion();
    }

    @DeleteMapping("/{usuarioId}/{publicacionId}")
    public void eliminarVistaPublicacion(@PathVariable Long usuarioId, @PathVariable Long publicacionId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        service.eliminarVistaPublicacion(usuarioId, publicacionId, fecha);
    }
}
