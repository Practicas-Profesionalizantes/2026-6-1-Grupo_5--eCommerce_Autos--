package com.back.carfind.controller;

import com.back.carfind.model.HistorialVehiculo;
import com.back.carfind.service.HistorialVehiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-vehiculos")
@CrossOrigin(origins = "*")
public class HistorialVehiculoController {

    private final HistorialVehiculoService service;

    public HistorialVehiculoController(HistorialVehiculoService service) {
        this.service = service;
    }

    @PostMapping
    public HistorialVehiculo crearHistorialVehiculo(@RequestBody HistorialVehiculo historialVehiculo) {
        return service.crearHistorialVehiculo(historialVehiculo);
    }

    @GetMapping("/{id}")
    public HistorialVehiculo obtenerHistorialVehiculo(@PathVariable Long id) {
        return service.obtenerHistorialVehiculo(id);
    }

    @GetMapping
    public List<HistorialVehiculo> listarHistorialVehiculos() {
        return service.listarHistorialesVehiculo();
    }

    @PutMapping("/{id}")
    public HistorialVehiculo actualizarHistorialVehiculo(@PathVariable Long id, @RequestBody HistorialVehiculo historialVehiculo) {
        return service.actualizarHistorialVehiculo(id, historialVehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorialVehiculo(@PathVariable Long id) {
        service.eliminarHistorialVehiculo(id);
    }
}
