package com.back.carfind.controller;

import com.back.carfind.model.Pagos;
import com.back.carfind.service.PagosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    private final PagosService service;

    public PagosController(PagosService service) {
        this.service = service;
    }

    @PostMapping
    public Pagos crearPago(@RequestBody Pagos pago) {
        return service.crearPago(pago);
    }

    @GetMapping("/{id}")
    public Pagos obtenerPago(@PathVariable Long id) {
        return service.obtenerPago(id);
    }

    @GetMapping
    public List<Pagos> listarPagos() {
        return service.listarPagos();
    }

    @PutMapping("/{id}")
    public Pagos actualizarPago(@PathVariable Long id, @RequestBody Pagos pago) {
        return service.actualizarPago(id, pago);
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Long id) {
        service.eliminarPago(id);
    }
}
