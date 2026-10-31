package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Variantes;
import com.back.carfind.repository.VariantesRepository;

@Service
public class VariantesService {

    @Autowired
    private VariantesRepository repository;

    public Variantes crearVariante(Variantes variante) {
        return repository.save(variante);
    }

    public Variantes obtenerVariante(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Variante no encontrada"
                ));
    }

    public List<Variantes> listarVariantes() {
        return repository.findAll();
    }

    public Variantes actualizarVariante(
            Long id,
            Variantes datos) {

        Variantes variante = obtenerVariante(id);

        variante.setPublicacion_id(
                datos.getPublicacion_id()
        );

        variante.setNombre(datos.getNombre());

        return repository.save(variante);
    }

    public void eliminarVariante(Long id) {
        Variantes variante = obtenerVariante(id);

        repository.delete(variante);
    }
}