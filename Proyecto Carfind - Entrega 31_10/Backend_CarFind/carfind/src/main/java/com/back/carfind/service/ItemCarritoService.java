package com.back.carfind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.carfind.model.ItemCarrito;
import com.back.carfind.model.ItemCarritoId;
import com.back.carfind.repository.ItemCarritoRepository;

@Service
public class ItemCarritoService {

    @Autowired
    private ItemCarritoRepository repository;

    public ItemCarrito crearItemCarrito(ItemCarrito itemCarrito) {
        return repository.save(itemCarrito);
    }

    public ItemCarrito obtenerItemCarrito(Long usuarioId, Long publicacionId) {
        return repository.findById(new ItemCarritoId(usuarioId, publicacionId))
                .orElseThrow(() -> new RuntimeException("Item de carrito no encontrado"));
    }

    public List<ItemCarrito> listarItemsCarrito() {
        return repository.findAll();
    }

    public void eliminarItemCarrito(Long usuarioId, Long publicacionId) {
        ItemCarrito itemCarrito = obtenerItemCarrito(usuarioId, publicacionId);
        repository.delete(itemCarrito);
    }
}
