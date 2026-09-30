package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Modelos;
import com.back.carfind.repository.ModelosRepository;

@Service
public class ModelosService {

    @Autowired
    private ModelosRepository repository;

    public Modelos crearModelo(Modelos modelo) {
        return repository.save(modelo);
    }

    public Modelos obtenerModelo(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Modelo no encontrado"
                ));
    }

    public List<Modelos> listarModelos() {
        return repository.findAll();
    }

    public Modelos actualizarModelo(Long id, Modelos datos) {
        Modelos modelo = obtenerModelo(id);

        modelo.setNombre_modelo(datos.getNombre_modelo());
        modelo.setMarca_id(datos.getMarca_id());

        return repository.save(modelo);
    }

    public void eliminarModelo(Long id) {
        Modelos modelo = obtenerModelo(id);

        repository.delete(modelo);
    }
}