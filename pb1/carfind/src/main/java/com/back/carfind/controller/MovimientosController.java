package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Movimientos;
import com.back.carfind.service.MovimientosService;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientosController {

    private final MovimientosService service;

    public MovimientosController(MovimientosService service) {
        this.service = service;
    }

    @PostMapping
    public Movimientos crearMovimiento(@RequestBody Movimientos movimiento) {
        return service.crearMovimiento(movimiento);
    }

    @GetMapping("/{id}")
    public Movimientos obtenerMovimiento(@PathVariable Long id) {
        return service.obtenerMovimiento(id);
    }

    @GetMapping
    public List<Movimientos> listarMovimientos() {
        return service.listarMovimientos();
    }

    @PutMapping("/{id}")
    public Movimientos actualizarMovimiento(@PathVariable Long id, @RequestBody Movimientos movimiento) {
        return service.actualizarMovimiento(id, movimiento);
    }

    @DeleteMapping("/{id}")
    public void eliminarMovimiento(@PathVariable Long id) {
        service.eliminarMovimiento(id);
    }
}
