package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Favoritos;
import com.back.carfind.service.FavoritosService;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritosController {

    private final FavoritosService service;

    public FavoritosController(FavoritosService service) {
        this.service = service;
    }

    @PostMapping
    public Favoritos crearFavorito(@RequestBody Favoritos favorito) {
        return service.crearFavorito(favorito);
    }

    @GetMapping("/{usuarioId}/{publicacionId}")
    public Favoritos obtenerFavorito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        return service.obtenerFavorito(usuarioId, publicacionId);
    }

    @GetMapping
    public List<Favoritos> listarFavoritos() {
        return service.listarFavoritos();
    }

    @DeleteMapping("/{usuarioId}/{publicacionId}")
    public void eliminarFavorito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        service.eliminarFavorito(usuarioId, publicacionId);
    }
}
