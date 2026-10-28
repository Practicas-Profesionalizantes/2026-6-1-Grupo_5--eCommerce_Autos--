package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.VisitasAuto;
import com.back.carfind.service.VisitasAutoService;

@RestController
@RequestMapping("/api/visitas-auto")
@CrossOrigin(origins = "*")
public class VisitasAutoController {

    private final VisitasAutoService service;

    public VisitasAutoController(VisitasAutoService service) {
        this.service = service;
    }

    @PostMapping
    public VisitasAuto crearVisitaAuto(@RequestBody VisitasAuto visitaAuto) {
        return service.crearVisitaAuto(visitaAuto);
    }

    @GetMapping("/{id}")
    public VisitasAuto obtenerVisitaAuto(@PathVariable Long id) {
        return service.obtenerVisitaAuto(id);
    }

    @GetMapping
    public List<VisitasAuto> listarVisitasAuto() {
        return service.listarVisitasAuto();
    }

    @PutMapping("/{id}")
    public VisitasAuto actualizarVisitaAuto(@PathVariable Long id, @RequestBody VisitasAuto visitaAuto) {
        return service.actualizarVisitaAuto(id, visitaAuto);
    }

    @DeleteMapping("/{id}")
    public void eliminarVisitaAuto(@PathVariable Long id) {
        service.eliminarVisitaAuto(id);
    }
}
