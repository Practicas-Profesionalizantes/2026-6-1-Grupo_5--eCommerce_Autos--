package com.back.carfind.controller;

import com.back.carfind.model.Billeteras;
import com.back.carfind.service.BilleterasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.crearBilleteras(billetera);
    }

    @GetMapping("/{id}")
    public Billeteras obtenerBilletera(@PathVariable Long id) {
        return service.obtenerBilleteras(id);
    }

    @GetMapping
    public List<Billeteras> listarBilleteras() {
        return service.listarBilleteras();
    }

    @PutMapping("/{id}")
    public Billeteras actualizarBilletera(@PathVariable Long id, @RequestBody Billeteras billetera) {
        return service.actualizarBilleteras(id, billetera);
    }

    @DeleteMapping("/{id}")
    public void eliminarBilletera(@PathVariable Long id) {
        service.eliminarbilleteras(id);
    }
}
