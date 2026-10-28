package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.BusquedasGuardadas;
import com.back.carfind.service.BusquedasGuardadasService;

@RestController
@RequestMapping("/api/busquedas-guardadas")
@CrossOrigin(origins = "*")
public class BusquedasGuardadasController {

    private final BusquedasGuardadasService service;

    public BusquedasGuardadasController(BusquedasGuardadasService service) {
        this.service = service;
    }

    @PostMapping
    public BusquedasGuardadas crearBusquedaGuardada(@RequestBody BusquedasGuardadas busquedaGuardada) {
        return service.crearBusquedaGuardada(busquedaGuardada);
    }

    @GetMapping("/{id}")
    public BusquedasGuardadas obtenerBusquedaGuardada(@PathVariable Long id) {
        return service.obtenerBusquedaGuardada(id);
    }

    @GetMapping
    public List<BusquedasGuardadas> listarBusquedasGuardadas() {
        return service.listarBusquedasGuardadas();
    }

    @PutMapping("/{id}")
    public BusquedasGuardadas actualizarBusquedaGuardada(@PathVariable Long id, @RequestBody BusquedasGuardadas busquedaGuardada) {
        return service.actualizarBusquedaGuardada(id, busquedaGuardada);
    }

    @DeleteMapping("/{id}")
    public void eliminarBusquedaGuardada(@PathVariable Long id) {
        service.eliminarBusquedaGuardada(id);
    }
}
