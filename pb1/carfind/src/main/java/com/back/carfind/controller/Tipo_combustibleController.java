package com.back.carfind.controller;

import com.back.carfind.model.Tipo_combustible;
import com.back.carfind.service.Tipo_combustibleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-combustible")
@CrossOrigin(origins = "*")
public class Tipo_combustibleController {

    private final Tipo_combustibleService service;

    public Tipo_combustibleController(Tipo_combustibleService service) {
        this.service = service;
    }

    @PostMapping
    public Tipo_combustible crearTipoCombustible(@RequestBody Tipo_combustible tipoCombustible) {
        return service.crearTipoCombustible(tipoCombustible);
    }

    @GetMapping("/{id}")
    public Tipo_combustible obtenerTipoCombustible(@PathVariable Long id) {
        return service.obtenerTipoCombustible(id);
    }

    @GetMapping
    public List<Tipo_combustible> listarTiposCombustible() {
        return service.listarTiposCombustible();
    }

    @PutMapping("/{id}")
    public Tipo_combustible actualizarTipoCombustible(@PathVariable Long id, @RequestBody Tipo_combustible tipoCombustible) {
        return service.actualizarTipoCombustible(id, tipoCombustible);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoCombustible(@PathVariable Long id) {
        service.eliminarTipoCombustible(id);
    }
}
