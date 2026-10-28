package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.PagosCuotas;
import com.back.carfind.service.PagosCuotasService;

@RestController
@RequestMapping("/api/pagos-cuotas")
@CrossOrigin(origins = "*")
public class PagosCuotasController {

    private final PagosCuotasService service;

    public PagosCuotasController(PagosCuotasService service) {
        this.service = service;
    }

    @PostMapping
    public PagosCuotas crearPagoCuota(@RequestBody PagosCuotas pagoCuota) {
        return service.crearPagoCuota(pagoCuota);
    }

    @GetMapping("/{id}")
    public PagosCuotas obtenerPagoCuota(@PathVariable Long id) {
        return service.obtenerPagoCuota(id);
    }

    @GetMapping
    public List<PagosCuotas> listarPagosCuotas() {
        return service.listarPagosCuotas();
    }

    @PutMapping("/{id}")
    public PagosCuotas actualizarPagoCuota(@PathVariable Long id, @RequestBody PagosCuotas pagoCuota) {
        return service.actualizarPagoCuota(id, pagoCuota);
    }

    @DeleteMapping("/{id}")
    public void eliminarPagoCuota(@PathVariable Long id) {
        service.eliminarPagoCuota(id);
    }
}
