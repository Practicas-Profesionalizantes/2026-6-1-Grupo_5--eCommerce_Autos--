package com.back.carfind.service;

import java.time.LocalDate;
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
        if (factura.getFehca_factura() == null) {
            factura.setFehca_factura(LocalDate.now());
        }

        return repository.save(factura);
    }

    public Facturas obtenerFactura(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Factura no encontrada"
                ));
    }

    public List<Facturas> listarFacturas() {
        return repository.findAll();
    }

    public Facturas actualizarFactura(Long id, Facturas datos) {
        Facturas factura = obtenerFactura(id);

        factura.setId_usuario(datos.getId_usuario());
        factura.setId_compra(datos.getId_compra());
        factura.setTotal_factura(datos.getTotal_factura());
        factura.setFehca_factura(datos.getFehca_factura());

        return repository.save(factura);
    }

    public void eliminarFactura(Long id) {
        Facturas factura = obtenerFactura(id);
        repository.delete(factura);
    }
}