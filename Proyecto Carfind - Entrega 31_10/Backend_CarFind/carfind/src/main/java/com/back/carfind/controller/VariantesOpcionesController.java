package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.VariantesOpcionesRequestDTO;
import com.back.carfind.dtos.VariantesOpcionesResponseDTO;
import com.back.carfind.model.VariantesOpciones;
import com.back.carfind.service.VariantesOpcionesService;

@RestController
@RequestMapping("/api/variantes-opciones")
@CrossOrigin(origins = "*")
public class VariantesOpcionesController {

    private final VariantesOpcionesService service;
    private final ModelMapper mapper;

    public VariantesOpcionesController(VariantesOpcionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public VariantesOpcionesResponseDTO crearVarianteOpcion(@RequestBody VariantesOpcionesRequestDTO dto) {
        return aDto(service.crearVarianteOpcion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public VariantesOpcionesResponseDTO obtenerVarianteOpcion(@PathVariable Long id) {
        return aDto(service.obtenerVarianteOpcion(id));
    }

    @GetMapping
    public List<VariantesOpcionesResponseDTO> listarVariantesOpciones() {
        return service.listarVariantesOpciones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public VariantesOpcionesResponseDTO actualizarVarianteOpcion(@PathVariable Long id, @RequestBody VariantesOpcionesRequestDTO dto) {
        return aDto(service.actualizarVarianteOpcion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarVarianteOpcion(@PathVariable Long id) {
        service.eliminarVarianteOpcion(id);
    }

    private VariantesOpciones aEntidad(VariantesOpcionesRequestDTO dto) {
        return mapper.map(dto, VariantesOpciones.class);
    }

    private VariantesOpcionesResponseDTO aDto(VariantesOpciones entidad) {
        return mapper.map(entidad, VariantesOpcionesResponseDTO.class);
    }
}
