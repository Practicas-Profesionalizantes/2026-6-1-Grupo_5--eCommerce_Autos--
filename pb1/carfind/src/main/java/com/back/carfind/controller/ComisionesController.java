package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Comisiones;
import com.back.carfind.service.ComisionesService;

@RestController
@RequestMapping("/api/comisiones")
@CrossOrigin(origins = "*")
public class ComisionesController {

    private final ComisionesService service;

    public ComisionesController(ComisionesService service) {
        this.service = service;
    }

    @PostMapping
    public Comisiones crearComision(@RequestBody Comisiones comision) {
        return service.crearComision(comision);
    }

    @GetMapping("/{id}")
    public Comisiones obtenerComision(@PathVariable Long id) {
        return service.obtenerComision(id);
    }

    @GetMapping
    public List<Comisiones> listarComisiones() {
        return service.listarComisiones();
    }

    @PutMapping("/{id}")
    public Comisiones actualizarComision(@PathVariable Long id, @RequestBody Comisiones comision) {
        return service.actualizarComision(id, comision);
    }

    @DeleteMapping("/{id}")
    public void eliminarComision(@PathVariable Long id) {
        service.eliminarComision(id);
    }
}
