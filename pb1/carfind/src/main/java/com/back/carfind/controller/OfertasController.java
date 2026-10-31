package com.back.carfind.controller;

import com.back.carfind.model.Ofertas;
import com.back.carfind.service.OfertasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertasController {

    private final OfertasService service;

    public OfertasController(OfertasService service) {
        this.service = service;
    }

    @PostMapping
    public Ofertas crearOferta(@RequestBody Ofertas oferta) {
        return service.crearOferta(oferta);
    }

    @GetMapping("/{id}")
    public Ofertas obtenerOferta(@PathVariable Long id) {
        return service.obtenerOferta(id);
    }

    @GetMapping
    public List<Ofertas> listarOfertas() {
        return service.listarOfertas();
    }

    @PutMapping("/{id}")
    public Ofertas actualizarOferta(@PathVariable Long id, @RequestBody Ofertas oferta) {
        return service.actualizarOferta(id, oferta);
    }

    @DeleteMapping("/{id}")
    public void eliminarOferta(@PathVariable Long id) {
        service.eliminarOferta(id);
    }
}
