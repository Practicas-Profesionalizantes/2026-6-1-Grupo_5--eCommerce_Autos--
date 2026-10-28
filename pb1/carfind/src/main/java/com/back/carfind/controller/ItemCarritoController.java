package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.ItemCarrito;
import com.back.carfind.service.ItemCarritoService;

@RestController
@RequestMapping("/api/items-carrito")
@CrossOrigin(origins = "*")
public class ItemCarritoController {

    private final ItemCarritoService service;

    public ItemCarritoController(ItemCarritoService service) {
        this.service = service;
    }

    @PostMapping
    public ItemCarrito crearItemCarrito(@RequestBody ItemCarrito itemCarrito) {
        return service.crearItemCarrito(itemCarrito);
    }

    @GetMapping("/{usuarioId}/{publicacionId}")
    public ItemCarrito obtenerItemCarrito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        return service.obtenerItemCarrito(usuarioId, publicacionId);
    }

    @GetMapping
    public List<ItemCarrito> listarItemsCarrito() {
        return service.listarItemsCarrito();
    }

    @DeleteMapping("/{usuarioId}/{publicacionId}")
    public void eliminarItemCarrito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        service.eliminarItemCarrito(usuarioId, publicacionId);
    }
}
