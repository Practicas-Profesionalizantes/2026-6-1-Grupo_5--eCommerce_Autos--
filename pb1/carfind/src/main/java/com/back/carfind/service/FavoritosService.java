package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Favoritos;
import com.back.carfind.repository.FavoritosRepository;

@Service
public class FavoritosService {

    @Autowired
    private FavoritosRepository repository;

    public Favoritos crearFavorito(Favoritos favorito) {
        return repository.save(favorito);
    }

    public Favoritos obtenerFavorito(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Favorito no encontrado"
                ));
    }

    public List<Favoritos> listarFavoritos() {
        return repository.findAll();
    }

    public Favoritos actualizarFavorito(
            Long id,
            Favoritos datos) {

        Favoritos favorito = obtenerFavorito(id);

        favorito.setId_usuario(datos.getId_usuario());
        favorito.setId_publicacion(datos.getId_publicacion());

        return repository.save(favorito);
    }

    public void eliminarFavorito(Long id) {
        Favoritos favorito = obtenerFavorito(id);

        repository.delete(favorito);
    }
}