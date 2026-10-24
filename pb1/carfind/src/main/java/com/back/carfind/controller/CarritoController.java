package com.back.carfind.controller;

import com.back.carfind.model.Carrito;
import com.back.carfind.service.CarritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @PostMapping
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        return service.crearCarrito(carrito);
    }

    @GetMapping("/{id}")
    public Carrito obtenerCarrito(@PathVariable Long id) {
        return service.obtenerCarrito(id);
    }

    @GetMapping
    public List<Carrito> listarCarritos() {
        return service.listarCarrito();
    }

    @PutMapping("/{id}")
    public Carrito actualizarCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        return service.actualizarCarrito(id, carrito);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrito(@PathVariable Long id) {
        service.eliminarcarrito(id);
    }
}
