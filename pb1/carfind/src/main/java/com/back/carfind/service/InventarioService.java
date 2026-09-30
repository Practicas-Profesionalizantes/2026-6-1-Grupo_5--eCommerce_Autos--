package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Inventario;
import com.back.carfind.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repository;

    public Inventario crearInventario(Inventario inventario) {
        return repository.save(inventario);
    }

    public Inventario obtenerInventario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Inventario no encontrado"
                ));
    }

    public List<Inventario> listarInventarios() {
        return repository.findAll();
    }

    public Inventario actualizarInventario(
            Long id,
            Inventario datos) {

        Inventario inventario = obtenerInventario(id);

        inventario.setPublicacion_id(
                datos.getPublicacion_id()
        );

        inventario.setStock(
                datos.getStock()
        );

        return repository.save(inventario);
    }

    public void eliminarInventario(Long id) {
        Inventario inventario = obtenerInventario(id);

        repository.delete(inventario);
    }
}