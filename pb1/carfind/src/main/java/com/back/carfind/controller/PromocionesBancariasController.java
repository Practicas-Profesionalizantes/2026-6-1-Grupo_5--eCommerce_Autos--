package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.PromocionesBancarias;
import com.back.carfind.service.PromocionesBancariasService;

@RestController
@RequestMapping("/api/promociones-bancarias")
@CrossOrigin(origins = "*")
public class PromocionesBancariasController {

    private final PromocionesBancariasService service;

    public PromocionesBancariasController(PromocionesBancariasService service) {
        this.service = service;
    }

    @PostMapping
    public PromocionesBancarias crearPromocionBancaria(@RequestBody PromocionesBancarias promocionBancaria) {
        return service.crearPromocionBancaria(promocionBancaria);
    }

    @GetMapping("/{id}")
    public PromocionesBancarias obtenerPromocionBancaria(@PathVariable Long id) {
        return service.obtenerPromocionBancaria(id);
    }

    @GetMapping
    public List<PromocionesBancarias> listarPromocionesBancarias() {
        return service.listarPromocionesBancarias();
    }

    @PutMapping("/{id}")
    public PromocionesBancarias actualizarPromocionBancaria(@PathVariable Long id, @RequestBody PromocionesBancarias promocionBancaria) {
        return service.actualizarPromocionBancaria(id, promocionBancaria);
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocionBancaria(@PathVariable Long id) {
        service.eliminarPromocionBancaria(id);
    }
}
