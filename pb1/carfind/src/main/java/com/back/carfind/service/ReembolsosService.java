package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Reembolsos;
import com.back.carfind.repository.ReembolsosRepository;

@Service
public class ReembolsosService {

    @Autowired
    private ReembolsosRepository repository;

    public Reembolsos crearReembolso(Reembolsos reembolso) {
        return repository.save(reembolso);
    }

    public Reembolsos obtenerReembolso(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Reembolso no encontrado"
                ));
    }

    public List<Reembolsos> listarReembolsos() {
        return repository.findAll();
    }

    public Reembolsos actualizarReembolso(
            Long id,
            Reembolsos datos) {

        Reembolsos reembolso = obtenerReembolso(id);

        reembolso.setId_pago(datos.getId_pago());
        reembolso.setMonto(datos.getMonto());
        reembolso.setMotivo(datos.getMotivo());
        reembolso.setFecha(datos.getFecha());

        return repository.save(reembolso);
    }

    public void eliminarReembolso(Long id) {
        Reembolsos reembolso = obtenerReembolso(id);

        repository.delete(reembolso);
    }
}