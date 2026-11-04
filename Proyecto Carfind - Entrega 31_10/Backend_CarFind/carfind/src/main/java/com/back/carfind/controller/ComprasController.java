package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ComprasRequestDTO;
import com.back.carfind.dtos.ComprasResponseDTO;
import com.back.carfind.model.Compras;
import com.back.carfind.service.ComprasService;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class ComprasController {

    private final ComprasService service;
    private final ModelMapper mapper;

    public ComprasController(ComprasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ComprasResponseDTO crearCompra(@RequestBody ComprasRequestDTO dto) {
        return aDto(service.crearCompra(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ComprasResponseDTO obtenerCompra(@PathVariable Long id) {
        return aDto(service.obtenerCompra(id));
    }

    @GetMapping
    public List<ComprasResponseDTO> listarCompras() {
        return service.listarCompras().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ComprasResponseDTO actualizarCompra(@PathVariable Long id, @RequestBody ComprasRequestDTO dto) {
        return aDto(service.actualizarCompra(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarCompra(@PathVariable Long id) {
        service.eliminarCompra(id);
    }

    private Compras aEntidad(ComprasRequestDTO dto) {
        return mapper.map(dto, Compras.class);
    }

    private ComprasResponseDTO aDto(Compras entidad) {
        return mapper.map(entidad, ComprasResponseDTO.class);
    }
}
