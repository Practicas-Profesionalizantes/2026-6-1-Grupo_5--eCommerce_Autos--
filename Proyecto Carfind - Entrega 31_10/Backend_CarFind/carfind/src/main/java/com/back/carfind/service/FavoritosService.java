package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Favoritos;
import com.back.carfind.model.FavoritosId;
import com.back.carfind.repository.FavoritosRepository;

@Service
public class FavoritosService {

    @Autowired
    private FavoritosRepository repository;

    public Favoritos crearFavorito(Favoritos favorito) {
        return repository.save(favorito);
    }

    public Favoritos obtenerFavorito(Long usuarioId, Long publicacionId) {
        return repository.findById(new FavoritosId(usuarioId, publicacionId))
                .orElseThrow(() -> new RuntimeException("Favorito no encontrado"));
    }

    public List<Favoritos> listarFavoritos() {
        return repository.findAll();
    }

    public void eliminarFavorito(Long usuarioId, Long publicacionId) {
        Favoritos favorito = obtenerFavorito(usuarioId, publicacionId);
        repository.delete(favorito);
    }
}
