package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.TipoPago;
import com.back.carfind.service.TipoPagoService;

@RestController
@RequestMapping("/api/tipos-pago")
@CrossOrigin(origins = "*")
public class TipoPagoController {

    private final TipoPagoService service;

    public TipoPagoController(TipoPagoService service) {
        this.service = service;
    }

    @PostMapping
    public TipoPago crearTipoPago(@RequestBody TipoPago tipoPago) {
        return service.crearTipoPago(tipoPago);
    }

    @GetMapping("/{id}")
    public TipoPago obtenerTipoPago(@PathVariable Long id) {
        return service.obtenerTipoPago(id);
    }

    @GetMapping
    public List<TipoPago> listarTiposPago() {
        return service.listarTiposPago();
    }

    @PutMapping("/{id}")
    public TipoPago actualizarTipoPago(@PathVariable Long id, @RequestBody TipoPago tipoPago) {
        return service.actualizarTipoPago(id, tipoPago);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoPago(@PathVariable Long id) {
        service.eliminarTipoPago(id);
    }
}
