package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Transacciones;
import com.back.carfind.service.TransaccionesService;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionesController {

    private final TransaccionesService service;

    public TransaccionesController(TransaccionesService service) {
        this.service = service;
    }

    @PostMapping
    public Transacciones crearTransaccion(@RequestBody Transacciones transaccion) {
        return service.crearTransaccion(transaccion);
    }

    @GetMapping("/{id}")
    public Transacciones obtenerTransaccion(@PathVariable Long id) {
        return service.obtenerTransaccion(id);
    }

    @GetMapping
    public List<Transacciones> listarTransacciones() {
        return service.listarTransacciones();
    }

    @PutMapping("/{id}")
    public Transacciones actualizarTransaccion(@PathVariable Long id, @RequestBody Transacciones transaccion) {
        return service.actualizarTransaccion(id, transaccion);
    }

    @DeleteMapping("/{id}")
    public void eliminarTransaccion(@PathVariable Long id) {
        service.eliminarTransaccion(id);
    }
}
