package com.back.carfind.controller;

import com.back.carfind.model.TiposPromocion;
import com.back.carfind.service.TiposPromocionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-promocion")
@CrossOrigin(origins = "*")
public class TipoPromocionController {

    private final TiposPromocionService service;

    public TipoPromocionController(TiposPromocionService service) {
        this.service = service;
    }

    @PostMapping
    public TiposPromocion crearTipoPromocion(@RequestBody TiposPromocion tipoPromocion) {
        return service.crearTipoPromocion(tipoPromocion);
    }

    @GetMapping("/{id}")
    public TiposPromocion obtenerTipoPromocion(@PathVariable Long id) {
        return service.obtenerTipoPromocion(id);
    }

    @GetMapping
    public List<TiposPromocion> listarTiposPromocion() {
        return service.listarTiposPromocion();
    }

    @PutMapping("/{id}")
    public TiposPromocion actualizarTipoPromocion(@PathVariable Long id, @RequestBody TiposPromocion tipoPromocion) {
        return service.actualizarTipoPromocion(id, tipoPromocion);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoPromocion(@PathVariable Long id) {
        service.eliminarTipoPromocion(id);
    }
}
