package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.HistorialPublicacionesRequestDTO;
import com.back.carfind.dtos.HistorialPublicacionesResponseDTO;
import com.back.carfind.model.HistorialPublicaciones;
import com.back.carfind.service.HistorialPublicacionesService;

@RestController
@RequestMapping("/api/historial-publicaciones")
@CrossOrigin(origins = "*")
public class HistorialPublicacionesController {

    private final HistorialPublicacionesService service;
    private final ModelMapper mapper;

    public HistorialPublicacionesController(HistorialPublicacionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public HistorialPublicacionesResponseDTO crearHistorialPublicacion(@RequestBody HistorialPublicacionesRequestDTO dto) {
        return aDto(service.crearHistorialPublicacion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public HistorialPublicacionesResponseDTO obtenerHistorialPublicacion(@PathVariable Long id) {
        return aDto(service.obtenerHistorialPublicacion(id));
    }

    @GetMapping
    public List<HistorialPublicacionesResponseDTO> listarHistorialPublicaciones() {
        return service.listarHistorialPublicaciones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public HistorialPublicacionesResponseDTO actualizarHistorialPublicacion(@PathVariable Long id, @RequestBody HistorialPublicacionesRequestDTO dto) {
        return aDto(service.actualizarHistorialPublicacion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorialPublicacion(@PathVariable Long id) {
        service.eliminarHistorialPublicacion(id);
    }

    private HistorialPublicaciones aEntidad(HistorialPublicacionesRequestDTO dto) {
        return mapper.map(dto, HistorialPublicaciones.class);
    }

    private HistorialPublicacionesResponseDTO aDto(HistorialPublicaciones entidad) {
        return mapper.map(entidad, HistorialPublicacionesResponseDTO.class);
    }
}
