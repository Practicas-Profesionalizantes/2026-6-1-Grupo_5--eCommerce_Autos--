package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.DireccionesRequestDTO;
import com.back.carfind.dtos.DireccionesResponseDTO;
import com.back.carfind.model.Direcciones;
import com.back.carfind.service.DireccionesService;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionesController {

    private final DireccionesService service;
    private final ModelMapper mapper;

    public DireccionesController(DireccionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public DireccionesResponseDTO crearDireccion(@RequestBody DireccionesRequestDTO dto) {
        return aDto(service.crearDireccion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public DireccionesResponseDTO obtenerDireccion(@PathVariable Long id) {
        return aDto(service.obtenerDireccion(id));
    }

    @GetMapping
    public List<DireccionesResponseDTO> listarDirecciones() {
        return service.listarDirecciones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public DireccionesResponseDTO actualizarDireccion(@PathVariable Long id, @RequestBody DireccionesRequestDTO dto) {
        return aDto(service.actualizarDireccion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarDireccion(@PathVariable Long id) {
        service.eliminarDireccion(id);
    }

    private Direcciones aEntidad(DireccionesRequestDTO dto) {
        return mapper.map(dto, Direcciones.class);
    }

    private DireccionesResponseDTO aDto(Direcciones entidad) {
        return mapper.map(entidad, DireccionesResponseDTO.class);
    }
}
