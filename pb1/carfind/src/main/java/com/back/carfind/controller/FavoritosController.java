 package com.back.carfind.controller;

import com.back.carfind.model.Favoritos;
import com.back.carfind.service.FavoritosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Favoritos obtenerFavorito(@PathVariable Long id) {
        return service.obtenerFavorito(id);
    }

    @GetMapping
    public List<Favoritos> listarFavoritos() {
        return service.listarFavoritos();
    }

    @PutMapping("/{id}")
    public Favoritos actualizarFavorito(@PathVariable Long id, @RequestBody Favoritos favorito) {
        return service.actualizarFavorito(id, favorito);
    }

    @DeleteMapping("/{id}")
    public void eliminarFavorito(@PathVariable Long id) {
        service.eliminarFavorito(id);
    }
}
