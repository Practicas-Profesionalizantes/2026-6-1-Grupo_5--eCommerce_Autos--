package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.PagosPromocion;
import com.back.carfind.repository.PagosPromocionesRepository;

@Service
public class PagosPromocionService {

    @Autowired
    private PagosPromocionesRepository repository;

    public PagosPromocion crearPagoPromocion(
            PagosPromocion pagoPromocion) {

        return repository.save(pagoPromocion);
    }

    public PagosPromocion obtenerPagoPromocion(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Pago de promoción no encontrado"
                ));
    }

    public List<PagosPromocion> listarPagosPromocion() {
        return repository.findAll();
    }

    public PagosPromocion actualizarPagoPromocion(
            Long id,
            PagosPromocion datos) {

        PagosPromocion pagoPromocion =
                obtenerPagoPromocion(id);

        pagoPromocion.setMonto(datos.getMonto());
        pagoPromocion.setEstado(datos.getEstado());
        pagoPromocion.setFecha(datos.getFecha());

        return repository.save(pagoPromocion);
    }

    public void eliminarPagoPromocion(Long id) {
        PagosPromocion pagoPromocion =
                obtenerPagoPromocion(id);

        repository.delete(pagoPromocion);
    }
}