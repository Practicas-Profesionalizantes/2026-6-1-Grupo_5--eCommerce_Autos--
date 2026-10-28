package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.PlanesCuotas;
import com.back.carfind.service.PlanesCuotasService;

@RestController
@RequestMapping("/api/planes-cuotas")
@CrossOrigin(origins = "*")
public class PlanesCuotasController {

    private final PlanesCuotasService service;

    public PlanesCuotasController(PlanesCuotasService service) {
        this.service = service;
    }

    @PostMapping
    public PlanesCuotas crearPlanCuotas(@RequestBody PlanesCuotas planCuotas) {
        return service.crearPlanCuotas(planCuotas);
    }

    @GetMapping("/{id}")
    public PlanesCuotas obtenerPlanCuotas(@PathVariable Long id) {
        return service.obtenerPlanCuotas(id);
    }

    @GetMapping
    public List<PlanesCuotas> listarPlanesCuotas() {
        return service.listarPlanesCuotas();
    }

    @PutMapping("/{id}")
    public PlanesCuotas actualizarPlanCuotas(@PathVariable Long id, @RequestBody PlanesCuotas planCuotas) {
        return service.actualizarPlanCuotas(id, planCuotas);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanCuotas(@PathVariable Long id) {
        service.eliminarPlanCuotas(id);
    }
}
