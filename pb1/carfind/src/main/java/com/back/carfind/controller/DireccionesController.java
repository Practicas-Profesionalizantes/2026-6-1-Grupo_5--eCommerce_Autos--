package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Direcciones;
import com.back.carfind.service.DireccionesService;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionesController {

    private final DireccionesService service;

    public DireccionesController(DireccionesService service) {
        this.service = service;
    }

    @PostMapping
    public Direcciones crearDireccion(@RequestBody Direcciones direccion) {
        return service.crearDireccion(direccion);
    }

    @GetMapping("/{id}")
    public Direcciones obtenerDireccion(@PathVariable Long id) {
        return service.obtenerDireccion(id);
    }

    @GetMapping
    public List<Direcciones> listarDirecciones() {
        return service.listarDirecciones();
    }

    @PutMapping("/{id}")
    public Direcciones actualizarDireccion(@PathVariable Long id, @RequestBody Direcciones direccion) {
        return service.actualizarDireccion(id, direccion);
    }

    @DeleteMapping("/{id}")
    public void eliminarDireccion(@PathVariable Long id) {
        service.eliminarDireccion(id);
    }
}
