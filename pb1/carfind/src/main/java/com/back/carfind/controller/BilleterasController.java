package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.Billeteras;
import com.back.carfind.service.BilleterasService;

@RestController
@RequestMapping("/api/billeteras")
@CrossOrigin(origins = "*")
public class BilleterasController {

    private final BilleterasService service;

    public BilleterasController(BilleterasService service) {
        this.service = service;
    }

    @PostMapping
    public Billeteras crearBilletera(@RequestBody Billeteras billetera) {
        return service.crearBilletera(billetera);
    }

    @GetMapping("/{id}")
    public Billeteras obtenerBilletera(@PathVariable Long id) {
        return service.obtenerBilletera(id);
    }

    @GetMapping
    public List<Billeteras> listarBilleteras() {
        return service.listarBilleteras();
    }

    @PutMapping("/{id}")
    public Billeteras actualizarBilletera(@PathVariable Long id, @RequestBody Billeteras billetera) {
        return service.actualizarBilletera(id, billetera);
    }

    @DeleteMapping("/{id}")
    public void eliminarBilletera(@PathVariable Long id) {
        service.eliminarBilletera(id);
    }
}
