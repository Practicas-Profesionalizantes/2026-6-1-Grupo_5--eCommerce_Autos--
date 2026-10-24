package com.back.carfind.controller;

import com.back.carfind.model.ModeracionMultimedia;
import com.back.carfind.service.ModeracionMultimediaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.crearModeracion(moderacionMultimedia);
    }

    @GetMapping("/{id}")
    public ModeracionMultimedia obtenerModeracionMultimedia(@PathVariable Long id) {
        return service.obtenerModeracion(id);
    }

    @GetMapping
    public List<ModeracionMultimedia> listarModeracionMultimedia() {
        return service.listarModeraciones();
    }

    @PutMapping("/{id}")
    public ModeracionMultimedia actualizarModeracionMultimedia(@PathVariable Long id, @RequestBody ModeracionMultimedia moderacionMultimedia) {
        return service.actualizarModeracion(id, moderacionMultimedia);
    }

    @DeleteMapping("/{id}")
    public void eliminarModeracionMultimedia(@PathVariable Long id) {
        service.eliminarModeracion(id);
    }
}
