package com.back.carfind.controller;

import com.back.carfind.model.Inventario;
import com.back.carfind.service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @PostMapping
    public Inventario crearInventario(@RequestBody Inventario inventario) {
        return service.crearInventario(inventario);
    }

    @GetMapping("/{id}")
    public Inventario obtenerInventario(@PathVariable Long id) {
        return service.obtenerInventario(id);
    }

    @GetMapping
    public List<Inventario> listarInventarios() {
        return service.listarInventarios();
    }

    @PutMapping("/{id}")
    public Inventario actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        return service.actualizarInventario(id, inventario);
    }

    @DeleteMapping("/{id}")
    public void eliminarInventario(@PathVariable Long id) {
        service.eliminarInventario(id);
    }
}
