package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.ModeracionMultimedia;
import com.back.carfind.service.ModeracionMultimediaService;

@RestController
@RequestMapping("/api/moderacion-multimedia")
@CrossOrigin(origins = "*")
public class ModeracionMultimediaController {

    private final ModeracionMultimediaService service;

    public ModeracionMultimediaController(ModeracionMultimediaService service) {
        this.service = service;
    }

    @PostMapping
    public ModeracionMultimedia crearModeracionMultimedia(@RequestBody ModeracionMultimedia moderacionMultimedia) {
        return service.crearModeracionMultimedia(moderacionMultimedia);
    }

    @GetMapping("/{id}")
    public ModeracionMultimedia obtenerModeracionMultimedia(@PathVariable Long id) {
        return service.obtenerModeracionMultimedia(id);
    }

    @GetMapping
    public List<ModeracionMultimedia> listarModeracionesMultimedia() {
        return service.listarModeracionesMultimedia();
    }

    @PutMapping("/{id}")
    public ModeracionMultimedia actualizarModeracionMultimedia(@PathVariable Long id, @RequestBody ModeracionMultimedia moderacionMultimedia) {
        return service.actualizarModeracionMultimedia(id, moderacionMultimedia);
    }

    @DeleteMapping("/{id}")
    public void eliminarModeracionMultimedia(@PathVariable Long id) {
        service.eliminarModeracionMultimedia(id);
    }
}
