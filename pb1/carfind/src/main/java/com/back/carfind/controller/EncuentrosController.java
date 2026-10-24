package com.back.carfind.controller;

import com.back.carfind.model.Encuentros;
import com.back.carfind.service.EncuentrosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encuentros")
@CrossOrigin(origins = "*")
public class EncuentrosController {

    private final EncuentrosService service;

    public EncuentrosController(EncuentrosService service) {
        this.service = service;
    }

    @PostMapping
    public Encuentros crearEncuentro(@RequestBody Encuentros encuentro) {
        return service.crearEncuentro(encuentro);
    }

    @GetMapping("/{id}")
    public Encuentros obtenerEncuentro(@PathVariable Long id) {
        return service.obtenerEncuentro(id);
    }

    @GetMapping
    public List<Encuentros> listarEncuentros() {
        return service.listarEncuentros();
    }

    @PutMapping("/{id}")
    public Encuentros actualizarEncuentro(@PathVariable Long id, @RequestBody Encuentros encuentro) {
        return service.actualizarEncuentro(id, encuentro);
    }

    @DeleteMapping("/{id}")
    public void eliminarEncuentro(@PathVariable Long id) {
        service.eliminarEncuentro(id);
    }
}
