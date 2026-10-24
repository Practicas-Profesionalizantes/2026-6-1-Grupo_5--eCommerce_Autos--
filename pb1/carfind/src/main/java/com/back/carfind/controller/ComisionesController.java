package com.back.carfind.controller;

import com.back.carfind.model.Comisiones;
import com.back.carfind.service.ComisionesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comisiones")
@CrossOrigin(origins = "*")
public class ComisionesController {

    private final ComisionesService service;

    public ComisionesController(ComisionesService service) {
        this.service = service;
    }

    @PostMapping
    public Comisiones crearComision(@RequestBody Comisiones comisiones) {
        return service.crearComisiones(comisiones);
    }

    @GetMapping("/{id}")
    public Comisiones obtenerComision(@PathVariable Long id) {
        return service.obtenerComisiones(id);
    }

    @GetMapping
    public List<Comisiones> listarComisiones() {
        return service.listarComisiones();
    }

    @PutMapping("/{id}")
    public Comisiones actualizarComision(@PathVariable Long id, @RequestBody Comisiones comisiones) {
        return service.actualizarComisiones(id, comisiones);
    }

    @DeleteMapping("/{id}")
    public void eliminarComision(@PathVariable Long id) {
        service.eliminarcomisiones(id);
    }
}
