package com.back.carfind.controller;

import com.back.carfind.model.PromocionesBancarias;
import com.back.carfind.service.PromocionesBancariasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.crearPromocion(promocionBancaria);
    }

    @GetMapping("/{id}")
    public PromocionesBancarias obtenerPromocionBancaria(@PathVariable Long id) {
        return service.obtenerPromocion(id);
    }

    @GetMapping
    public List<PromocionesBancarias> listarPromocionesBancarias() {
        return service.listarPromociones();
    }

    @PutMapping("/{id}")
    public PromocionesBancarias actualizarPromocionBancaria(@PathVariable Long id, @RequestBody PromocionesBancarias promocionBancaria) {
        return service.actualizarPromocion(id, promocionBancaria);
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocionBancaria(@PathVariable Long id) {
        service.eliminarPromocion(id);
    }
}
