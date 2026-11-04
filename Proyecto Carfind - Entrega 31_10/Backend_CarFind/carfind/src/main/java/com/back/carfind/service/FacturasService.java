package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.Facturas;
import com.back.carfind.repository.FacturasRepository;

@Service
public class FacturasService {

    @Autowired
    private FacturasRepository repository;

    public Facturas crearFactura(Facturas factura) {
        return repository.save(factura);
    }

    public Facturas obtenerFactura(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    public List<Facturas> listarFacturas() {
        return repository.findAll();
    }

    public Facturas actualizarFactura(Long id, Facturas datos) {
        Facturas factura = obtenerFactura(id);
        factura.setUsuarioId(datos.getUsuarioId());
        factura.setCompraId(datos.getCompraId());
        factura.setTotal(datos.getTotal());
        factura.setFecha(datos.getFecha());
        return repository.save(factura);
    }

    public void eliminarFactura(Long id) {
        Facturas factura = obtenerFactura(id);
        repository.delete(factura);
    }
}
