package com.back.carfind.controller;

import com.back.carfind.model.MetodosPago;
import com.back.carfind.service.MetodosPagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
@CrossOrigin(origins = "*")
public class MetodosPagoController {

    private final MetodosPagoService service;

    public MetodosPagoController(MetodosPagoService service) {
        this.service = service;
    }

    @PostMapping
    public MetodosPago crearMetodoPago(@RequestBody MetodosPago metodoPago) {
        return service.crearMetodoPago(metodoPago);
    }

    @GetMapping("/{id}")
    public MetodosPago obtenerMetodoPago(@PathVariable Long id) {
        return service.obtenerMetodoPago(id);
    }

    @GetMapping
    public List<MetodosPago> listarMetodosPago() {
        return service.listarMetodosPago();
    }

    @PutMapping("/{id}")
    public MetodosPago actualizarMetodoPago(@PathVariable Long id, @RequestBody MetodosPago metodoPago) {
        return service.actualizarMetodoPago(id, metodoPago);
    }

    @DeleteMapping("/{id}")
    public void eliminarMetodoPago(@PathVariable Long id) {
        service.eliminarMetodoPago(id);
    }
}
