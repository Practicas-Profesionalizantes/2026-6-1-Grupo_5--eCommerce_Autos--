package com.back.carfind.controller;

import com.back.carfind.model.VistasPublicacion;
import com.back.carfind.service.VistasPublicacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vistas-publicacion")
@CrossOrigin(origins = "*")
public class VistasPublicacionController {

    private final VistasPublicacionService service;

    public VistasPublicacionController(VistasPublicacionService service) {
        this.service = service;
    }

    @PostMapping
    public VistasPublicacion crearVistaPublicacion(@RequestBody VistasPublicacion vistasPublicacion) {
        return service.crearVista(vistasPublicacion);
    }

    @GetMapping("/{id}")
    public VistasPublicacion obtenerVistaPublicacion(@PathVariable Long id) {
        return service.obtenerVista(id);
    }

    @GetMapping
    public List<VistasPublicacion> listarVistasPublicacion() {
        return service.listarVistas();
    }

    @PutMapping("/{id}")
    public VistasPublicacion actualizarVistaPublicacion(@PathVariable Long id, @RequestBody VistasPublicacion vistasPublicacion) {
        return service.actualizarVista(id, vistasPublicacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarVistaPublicacion(@PathVariable Long id) {
        service.eliminarVista(id);
    }
}
