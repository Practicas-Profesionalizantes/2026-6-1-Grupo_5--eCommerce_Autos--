package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Multimedia;
import com.back.carfind.service.MultimediaService;

@RestController
@RequestMapping("/api/multimedia")
@CrossOrigin(origins = "*")
public class MultimediaController {

    private final MultimediaService service;

    public MultimediaController(MultimediaService service) {
        this.service = service;
    }

    @PostMapping
    public Multimedia crearMultimedia(@RequestBody Multimedia multimedia) {
        return service.crearMultimedia(multimedia);
    }

    @GetMapping("/{id}")
    public Multimedia obtenerMultimedia(@PathVariable Long id) {
        return service.obtenerMultimedia(id);
    }

    @GetMapping
    public List<Multimedia> listarMultimedia() {
        return service.listarMultimedia();
    }

    @PutMapping("/{id}")
    public Multimedia actualizarMultimedia(@PathVariable Long id, @RequestBody Multimedia multimedia) {
        return service.actualizarMultimedia(id, multimedia);
    }

    @DeleteMapping("/{id}")
    public void eliminarMultimedia(@PathVariable Long id) {
        service.eliminarMultimedia(id);
    }
}
