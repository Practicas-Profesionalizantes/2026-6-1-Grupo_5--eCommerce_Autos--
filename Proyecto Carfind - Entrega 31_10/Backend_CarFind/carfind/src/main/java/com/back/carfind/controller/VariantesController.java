package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.VariantesRequestDTO;
import com.back.carfind.dtos.VariantesResponseDTO;
import com.back.carfind.model.Variantes;
import com.back.carfind.service.VariantesService;

@RestController
@RequestMapping("/api/variantes")
@CrossOrigin(origins = "*")
public class VariantesController {

    private final VariantesService service;
    private final ModelMapper mapper;

    public VariantesController(VariantesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public VariantesResponseDTO crearVariante(@RequestBody VariantesRequestDTO dto) {
        return aDto(service.crearVariante(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public VariantesResponseDTO obtenerVariante(@PathVariable Long id) {
        return aDto(service.obtenerVariante(id));
    }

    @GetMapping
    public List<VariantesResponseDTO> listarVariantes() {
        return service.listarVariantes().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public VariantesResponseDTO actualizarVariante(@PathVariable Long id, @RequestBody VariantesRequestDTO dto) {
        return aDto(service.actualizarVariante(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarVariante(@PathVariable Long id) {
        service.eliminarVariante(id);
    }

    private Variantes aEntidad(VariantesRequestDTO dto) {
        return mapper.map(dto, Variantes.class);
    }

    private VariantesResponseDTO aDto(Variantes entidad) {
        return mapper.map(entidad, VariantesResponseDTO.class);
    }
}
