package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.ActividadUsuario;
import com.back.carfind.service.ActividadUsuarioService;

@RestController
@RequestMapping("/api/actividades-usuarios")
@CrossOrigin(origins = "*")
public class ActividadUsuarioController {

    private final ActividadUsuarioService service;

    public ActividadUsuarioController(ActividadUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ActividadUsuario crearActividadUsuario(@RequestBody ActividadUsuario actividadUsuario) {
        return service.crearActividadUsuario(actividadUsuario);
    }

    @GetMapping("/{id}")
    public ActividadUsuario obtenerActividadUsuario(@PathVariable Long id) {
        return service.obtenerActividadUsuario(id);
    }

    @GetMapping
    public List<ActividadUsuario> listarActividadesUsuario() {
        return service.listarActividadesUsuario();
    }

    @PutMapping("/{id}")
    public ActividadUsuario actualizarActividadUsuario(@PathVariable Long id, @RequestBody ActividadUsuario actividadUsuario) {
        return service.actualizarActividadUsuario(id, actividadUsuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarActividadUsuario(@PathVariable Long id) {
        service.eliminarActividadUsuario(id);
    }
}
