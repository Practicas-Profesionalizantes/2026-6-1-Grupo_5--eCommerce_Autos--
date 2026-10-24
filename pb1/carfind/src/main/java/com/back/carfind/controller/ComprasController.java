package com.back.carfind.controller;

import com.back.carfind.model.Compras;
import com.back.carfind.service.ComprasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class ComprasController {

    private final ComprasService service;

    public ComprasController(ComprasService service) {
        this.service = service;
    }

    @PostMapping
    public Compras crearCompra(@RequestBody Compras compra) {
        return service.crearCompras(compra);
    }

    @GetMapping("/{id}")
    public Compras obtenerCompra(@PathVariable Long id) {
        return service.obtenerCompras(id);
    }

    @GetMapping
    public List<Compras> listarCompras() {
        return service.listarCompras();
    }

    @PutMapping("/{id}")
    public Compras actualizarCompra(@PathVariable Long id, @RequestBody Compras compra) {
        return service.actualizarCompras(id, compra);
    }

    @DeleteMapping("/{id}")
    public void eliminarCompra(@PathVariable Long id) {
        service.eliminarcompras(id);
    }
}
