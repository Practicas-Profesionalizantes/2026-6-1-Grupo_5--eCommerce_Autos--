package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Autos;
import com.back.carfind.service.AutosService;

@RestController
@RequestMapping("/api/autos")
@CrossOrigin(origins = "*")
public class AutosController {

    private final AutosService service;

    public AutosController(AutosService service) {
        this.service = service;
    }

    @PostMapping
    public Autos crearAuto(@RequestBody Autos auto) {
        return service.crearAuto(auto);
    }

    @GetMapping("/{id}")
    public Autos obtenerAuto(@PathVariable Long id) {
        return service.obtenerAuto(id);
    }

    @GetMapping
    public List<Autos> listarAutos() {
        return service.listarAutos();
    }

    @PutMapping("/{id}")
    public Autos actualizarAuto(@PathVariable Long id, @RequestBody Autos auto) {
        return service.actualizarAuto(id, auto);
    }

    @DeleteMapping("/{id}")
    public void eliminarAuto(@PathVariable Long id) {
        service.eliminarAuto(id);
    }
}
