package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ItemCarritoRequestDTO;
import com.back.carfind.dtos.ItemCarritoResponseDTO;
import com.back.carfind.model.ItemCarrito;
import com.back.carfind.service.ItemCarritoService;

@RestController
@RequestMapping("/api/items-carrito")
@CrossOrigin(origins = "*")
public class ItemCarritoController {

    private final ItemCarritoService service;
    private final ModelMapper mapper;

    public ItemCarritoController(ItemCarritoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ItemCarritoResponseDTO crearItemCarrito(@RequestBody ItemCarritoRequestDTO dto) {
        return aDto(service.crearItemCarrito(aEntidad(dto)));
    }

    @GetMapping("/{usuarioId}/{publicacionId}")
    public ItemCarritoResponseDTO obtenerItemCarrito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        return aDto(service.obtenerItemCarrito(usuarioId, publicacionId));
    }

    @GetMapping
    public List<ItemCarritoResponseDTO> listarItemsCarrito() {
        return service.listarItemsCarrito().stream().map(this::aDto).toList();
    }

    @DeleteMapping("/{usuarioId}/{publicacionId}")
    public void eliminarItemCarrito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        service.eliminarItemCarrito(usuarioId, publicacionId);
    }

    private ItemCarrito aEntidad(ItemCarritoRequestDTO dto) {
        return mapper.map(dto, ItemCarrito.class);
    }

    private ItemCarritoResponseDTO aDto(ItemCarrito entidad) {
        return mapper.map(entidad, ItemCarritoResponseDTO.class);
    }
}
