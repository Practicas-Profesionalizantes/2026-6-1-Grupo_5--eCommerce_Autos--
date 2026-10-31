package com.back.carfind.controller;

import com.back.carfind.model.Autos;
import com.back.carfind.service.AutosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autos")
@CrossOrigin(origins = "*")
public class AutosController {

    private final AutosService service;

    public AutosController(AutosService service) {
        this.service = service;
    }

    @PostMapping
    public Autos crearAutos(@RequestBody Autos autos) {
        return service.crearAutos(autos);
    }

    @GetMapping("/{id}")
    public Autos obtenerAutos(@PathVariable Long id) {
        return service.obtenerAutos(id);
    }

    @GetMapping
    public List<Autos> listarAutos() {
        return service.listarAutos();
    }

    @PutMapping("/{id}")
    public Autos actualizarAutos(@PathVariable Long id, @RequestBody Autos autos) {
        return service.actualizarAutos(id, autos);
    }

    @DeleteMapping("/{id}")
    public void eliminarautos(@PathVariable Long id) {
        service.eliminarautos(id);
    }
}