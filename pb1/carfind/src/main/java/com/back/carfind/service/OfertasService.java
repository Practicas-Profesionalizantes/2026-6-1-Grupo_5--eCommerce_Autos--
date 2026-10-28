package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Ofertas;
import com.back.carfind.repository.OfertasRepository;

@Service
public class OfertasService {

    @Autowired
    private OfertasRepository repository;

    public Ofertas crearOferta(Ofertas oferta) {
        return repository.save(oferta);
    }

    public Ofertas obtenerOferta(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
    }

    public List<Ofertas> listarOfertas() {
        return repository.findAll();
    }

    public Ofertas actualizarOferta(Long id, Ofertas datos) {
        Ofertas oferta = obtenerOferta(id);
        oferta.setPublicacionId(datos.getPublicacionId());
        oferta.setCompradorId(datos.getCompradorId());
        oferta.setMonto(datos.getMonto());
        oferta.setEstado(datos.getEstado());
        oferta.setFecha(datos.getFecha());
        return repository.save(oferta);
    }

    public void eliminarOferta(Long id) {
        Ofertas oferta = obtenerOferta(id);
        repository.delete(oferta);
    }
}
