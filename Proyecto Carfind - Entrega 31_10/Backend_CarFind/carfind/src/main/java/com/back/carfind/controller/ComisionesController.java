package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ComisionesRequestDTO;
import com.back.carfind.dtos.ComisionesResponseDTO;
import com.back.carfind.model.Comisiones;
import com.back.carfind.service.ComisionesService;

@RestController
@RequestMapping("/api/comisiones")
@CrossOrigin(origins = "*")
public class ComisionesController {

    private final ComisionesService service;
    private final ModelMapper mapper;

    public ComisionesController(ComisionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ComisionesResponseDTO crearComision(@RequestBody ComisionesRequestDTO dto) {
        return aDto(service.crearComision(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ComisionesResponseDTO obtenerComision(@PathVariable Long id) {
        return aDto(service.obtenerComision(id));
    }

    @GetMapping
    public List<ComisionesResponseDTO> listarComisiones() {
        return service.listarComisiones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ComisionesResponseDTO actualizarComision(@PathVariable Long id, @RequestBody ComisionesRequestDTO dto) {
        return aDto(service.actualizarComision(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarComision(@PathVariable Long id) {
        service.eliminarComision(id);
    }

    private Comisiones aEntidad(ComisionesRequestDTO dto) {
        return mapper.map(dto, Comisiones.class);
    }

    private ComisionesResponseDTO aDto(Comisiones entidad) {
        return mapper.map(entidad, ComisionesResponseDTO.class);
    }
}
