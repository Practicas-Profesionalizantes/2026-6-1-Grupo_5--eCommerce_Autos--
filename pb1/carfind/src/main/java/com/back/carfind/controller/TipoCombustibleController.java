package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.TipoCombustible;
import com.back.carfind.service.TipoCombustibleService;

@RestController
@RequestMapping("/api/tipos-combustible")
@CrossOrigin(origins = "*")
public class TipoCombustibleController {

    private final TipoCombustibleService service;

    public TipoCombustibleController(TipoCombustibleService service) {
        this.service = service;
    }

    @PostMapping
    public TipoCombustible crearTipoCombustible(@RequestBody TipoCombustible tipoCombustible) {
        return service.crearTipoCombustible(tipoCombustible);
    }

    @GetMapping("/{id}")
    public TipoCombustible obtenerTipoCombustible(@PathVariable Long id) {
        return service.obtenerTipoCombustible(id);
    }

    @GetMapping
    public List<TipoCombustible> listarTiposCombustible() {
        return service.listarTiposCombustible();
    }

    @PutMapping("/{id}")
    public TipoCombustible actualizarTipoCombustible(@PathVariable Long id, @RequestBody TipoCombustible tipoCombustible) {
        return service.actualizarTipoCombustible(id, tipoCombustible);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoCombustible(@PathVariable Long id) {
        service.eliminarTipoCombustible(id);
    }
}
