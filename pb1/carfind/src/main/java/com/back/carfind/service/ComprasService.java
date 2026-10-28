package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Compras;
import com.back.carfind.repository.ComprasRepository;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository repository;

    public Compras crearCompra(Compras compra) {
        return repository.save(compra);
    }

    public Compras obtenerCompra(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }

    public List<Compras> listarCompras() {
        return repository.findAll();
    }

    public Compras actualizarCompra(Long id, Compras datos) {
        Compras compra = obtenerCompra(id);
        compra.setCompradorId(datos.getCompradorId());
        compra.setPublicacionId(datos.getPublicacionId());
        compra.setPrecioFinal(datos.getPrecioFinal());
        compra.setEstado(datos.getEstado());
        compra.setFecha(datos.getFecha());
        return repository.save(compra);
    }

    public void eliminarCompra(Long id) {
        Compras compra = obtenerCompra(id);
        repository.delete(compra);
    }
}
