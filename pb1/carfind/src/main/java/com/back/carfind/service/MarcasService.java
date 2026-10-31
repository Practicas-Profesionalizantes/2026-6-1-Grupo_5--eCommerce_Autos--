package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Marcas;
import com.back.carfind.repository.MarcasRepository;

@Service
public class MarcasService {

    @Autowired
    private MarcasRepository repository;

    public Marcas crearMarca(Marcas marca) {
        return repository.save(marca);
    }

    public Marcas obtenerMarca(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Marca no encontrada"
                ));
    }

    public List<Marcas> listarMarcas() {
        return repository.findAll();
    }

    public Marcas actualizarMarca(Long id, Marcas datos) {
        Marcas marca = obtenerMarca(id);

        marca.setNombre_marca(datos.getNombre_marca());

        return repository.save(marca);
    }

    public void eliminarMarca(Long id) {
        Marcas marca = obtenerMarca(id);

        repository.delete(marca);
    }
}