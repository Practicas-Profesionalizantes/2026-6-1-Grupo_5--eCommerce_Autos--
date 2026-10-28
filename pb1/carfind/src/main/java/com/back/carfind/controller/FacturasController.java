package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Facturas;
import com.back.carfind.service.FacturasService;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturasController {

    private final FacturasService service;

    public FacturasController(FacturasService service) {
        this.service = service;
    }

    @PostMapping
    public Facturas crearFactura(@RequestBody Facturas factura) {
        return service.crearFactura(factura);
    }

    @GetMapping("/{id}")
    public Facturas obtenerFactura(@PathVariable Long id) {
        return service.obtenerFactura(id);
    }

    @GetMapping
    public List<Facturas> listarFacturas() {
        return service.listarFacturas();
    }

    @PutMapping("/{id}")
    public Facturas actualizarFactura(@PathVariable Long id, @RequestBody Facturas factura) {
        return service.actualizarFactura(id, factura);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        service.eliminarFactura(id);
    }
}
