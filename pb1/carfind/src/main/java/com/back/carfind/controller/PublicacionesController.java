package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Publicaciones;
import com.back.carfind.service.PublicacionesService;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionesController {

    private final PublicacionesService service;

    public PublicacionesController(PublicacionesService service) {
        this.service = service;
    }

    @PostMapping
    public Publicaciones crearPublicacion(@RequestBody Publicaciones publicacion) {
        return service.crearPublicacion(publicacion);
    }

    @GetMapping("/{id}")
    public Publicaciones obtenerPublicacion(@PathVariable Long id) {
        return service.obtenerPublicacion(id);
    }

    @GetMapping
    public List<Publicaciones> listarPublicaciones() {
        return service.listarPublicaciones();
    }

    @PutMapping("/{id}")
    public Publicaciones actualizarPublicacion(@PathVariable Long id, @RequestBody Publicaciones publicacion) {
        return service.actualizarPublicacion(id, publicacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarPublicacion(@PathVariable Long id) {
        service.eliminarPublicacion(id);
    }
}
