package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Promociones;
import com.back.carfind.service.PromocionesService;

@RestController
@RequestMapping("/api/promociones")
@CrossOrigin(origins = "*")
public class PromocionesController {

    private final PromocionesService service;

    public PromocionesController(PromocionesService service) {
        this.service = service;
    }

    @PostMapping
    public Promociones crearPromocion(@RequestBody Promociones promocion) {
        return service.crearPromocion(promocion);
    }

    @GetMapping("/{id}")
    public Promociones obtenerPromocion(@PathVariable Long id) {
        return service.obtenerPromocion(id);
    }

    @GetMapping
    public List<Promociones> listarPromociones() {
        return service.listarPromociones();
    }

    @PutMapping("/{id}")
    public Promociones actualizarPromocion(@PathVariable Long id, @RequestBody Promociones promocion) {
        return service.actualizarPromocion(id, promocion);
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocion(@PathVariable Long id) {
        service.eliminarPromocion(id);
    }
}
