package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.FacturasRequestDTO;
import com.back.carfind.dtos.FacturasResponseDTO;
import com.back.carfind.model.Facturas;
import com.back.carfind.service.FacturasService;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturasController {

    private final FacturasService service;
    private final ModelMapper mapper;

    public FacturasController(FacturasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public FacturasResponseDTO crearFactura(@RequestBody FacturasRequestDTO dto) {
        return aDto(service.crearFactura(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public FacturasResponseDTO obtenerFactura(@PathVariable Long id) {
        return aDto(service.obtenerFactura(id));
    }

    @GetMapping
    public List<FacturasResponseDTO> listarFacturas() {
        return service.listarFacturas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public FacturasResponseDTO actualizarFactura(@PathVariable Long id, @RequestBody FacturasRequestDTO dto) {
        return aDto(service.actualizarFactura(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        service.eliminarFactura(id);
    }

    private Facturas aEntidad(FacturasRequestDTO dto) {
        return mapper.map(dto, Facturas.class);
    }

    private FacturasResponseDTO aDto(Facturas entidad) {
        return mapper.map(entidad, FacturasResponseDTO.class);
    }
}
