package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Roles;
import com.back.carfind.service.RolesService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolesController {

    private final RolesService service;

    public RolesController(RolesService service) {
        this.service = service;
    }

    @PostMapping
    public Roles crearRol(@RequestBody Roles rol) {
        return service.crearRol(rol);
    }

    @GetMapping("/{id}")
    public Roles obtenerRol(@PathVariable Long id) {
        return service.obtenerRol(id);
    }

    @GetMapping
    public List<Roles> listarRoles() {
        return service.listarRoles();
    }

    @PutMapping("/{id}")
    public Roles actualizarRol(@PathVariable Long id, @RequestBody Roles rol) {
        return service.actualizarRol(id, rol);
    }

    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Long id) {
        service.eliminarRol(id);
    }
}
