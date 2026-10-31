package com.back.carfind.controller;

import com.back.carfind.model.Reportes;
import com.back.carfind.service.ReportesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportesController {

    private final ReportesService service;

    public ReportesController(ReportesService service) {
        this.service = service;
    }

    @PostMapping
    public Reportes crearReporte(@RequestBody Reportes reporte) {
        return service.crearReporte(reporte);
    }

    @GetMapping("/{id}")
    public Reportes obtenerReporte(@PathVariable Long id) {
        return service.obtenerReporte(id);
    }

    @GetMapping
    public List<Reportes> listarReportes() {
        return service.listarReportes();
    }

    @PutMapping("/{id}")
    public Reportes actualizarReporte(@PathVariable Long id, @RequestBody Reportes reporte) {
        return service.actualizarReporte(id, reporte);
    }

    @DeleteMapping("/{id}")
    public void eliminarReporte(@PathVariable Long id) {
        service.eliminarReporte(id);
    }
}
