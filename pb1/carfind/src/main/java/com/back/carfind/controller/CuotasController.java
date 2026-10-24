package com.back.carfind.controller;

import com.back.carfind.model.Cuotas;
import com.back.carfind.service.CuotasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuotas")
@CrossOrigin(origins = "*")
public class CuotasController {

    private final CuotasService service;

    public CuotasController(CuotasService service) {
        this.service = service;
    }

    @PostMapping
    public Cuotas crearCuota(@RequestBody Cuotas cuota) {
        return service.crearCuotas(cuota);
    }

    @GetMapping("/{id}")
    public Cuotas obtenerCuota(@PathVariable Long id) {
        return service.obtenerCuotas(id);
    }

    @GetMapping
    public List<Cuotas> listarCuotas() {
        return service.listarCuotas();
    }

    @PutMapping("/{id}")
    public Cuotas actualizarCuota(@PathVariable Long id, @RequestBody Cuotas cuota) {
        return service.actualizarCuotas(id, cuota);
    }

    @DeleteMapping("/{id}")
    public void eliminarCuota(@PathVariable Long id) {
        service.eliminarcuotas(id);
    }
}
