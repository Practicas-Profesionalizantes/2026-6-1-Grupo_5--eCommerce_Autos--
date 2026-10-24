package com.back.carfind.controller;

import com.back.carfind.model.Verificaciones;
import com.back.carfind.service.VerificacionesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verificaciones")
@CrossOrigin(origins = "*")
public class VerificacionesController {

    private final VerificacionesService service;

    public VerificacionesController(VerificacionesService service) {
        this.service = service;
    }

    @PostMapping
    public Verificaciones crearVerificacion(@RequestBody Verificaciones verificacion) {
        return service.crearVerificacion(verificacion);
    }

    @GetMapping("/{id}")
    public Verificaciones obtenerVerificacion(@PathVariable Long id) {
        return service.obtenerVerificacion(id);
    }

    @GetMapping
    public List<Verificaciones> listarVerificaciones() {
        return service.listarVerificaciones();
    }

    @PutMapping("/{id}")
    public Verificaciones actualizarVerificacion(@PathVariable Long id, @RequestBody Verificaciones verificacion) {
        return service.actualizarVerificacion(id, verificacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarVerificacion(@PathVariable Long id) {
        service.eliminarVerificacion(id);
    }
}
