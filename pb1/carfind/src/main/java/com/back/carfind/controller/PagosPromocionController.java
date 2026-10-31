package com.back.carfind.controller;

import com.back.carfind.model.PagosPromocion;
import com.back.carfind.service.PagosPromocionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos-promocion")
@CrossOrigin(origins = "*")
public class PagosPromocionController {

    private final PagosPromocionService service;

    public PagosPromocionController(PagosPromocionService service) {
        this.service = service;
    }

    @PostMapping
    public PagosPromocion crearPagoPromocion(@RequestBody PagosPromocion pagoPromocion) {
        return service.crearPagoPromocion(pagoPromocion);
    }

    @GetMapping("/{id}")
    public PagosPromocion obtenerPagoPromocion(@PathVariable Long id) {
        return service.obtenerPagoPromocion(id);
    }

    @GetMapping
    public List<PagosPromocion> listarPagosPromociones() {
        return service.listarPagosPromocion();
    }

    @PutMapping("/{id}")
    public PagosPromocion actualizarPagoPromocion(@PathVariable Long id, @RequestBody PagosPromocion pagoPromocion) {
        return service.actualizarPagoPromocion(id, pagoPromocion);
    }

    @DeleteMapping("/{id}")
    public void eliminarPagoPromocion(@PathVariable Long id) {
        service.eliminarPagoPromocion(id);
    }
}
