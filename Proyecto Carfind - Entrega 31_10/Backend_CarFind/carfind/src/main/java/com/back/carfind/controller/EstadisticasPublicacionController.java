package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.EstadisticasPublicacionRequestDTO;
import com.back.carfind.dtos.EstadisticasPublicacionResponseDTO;
import com.back.carfind.model.EstadisticasPublicacion;
import com.back.carfind.service.EstadisticasPublicacionService;

@RestController
@RequestMapping("/api/estadisticas-publicacion")
@CrossOrigin(origins = "*")
public class EstadisticasPublicacionController {

    private final EstadisticasPublicacionService service;
    private final ModelMapper mapper;

    public EstadisticasPublicacionController(EstadisticasPublicacionService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public EstadisticasPublicacionResponseDTO crearEstadistica(@RequestBody EstadisticasPublicacionRequestDTO dto) {
        return aDto(service.crearEstadistica(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public EstadisticasPublicacionResponseDTO obtenerEstadistica(@PathVariable Long id) {
        return aDto(service.obtenerEstadistica(id));
    }

    @GetMapping
    public List<EstadisticasPublicacionResponseDTO> listarEstadisticas() {
        return service.listarEstadisticas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public EstadisticasPublicacionResponseDTO actualizarEstadistica(@PathVariable Long id, @RequestBody EstadisticasPublicacionRequestDTO dto) {
        return aDto(service.actualizarEstadistica(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarEstadistica(@PathVariable Long id) {
        service.eliminarEstadistica(id);
    }

    private EstadisticasPublicacion aEntidad(EstadisticasPublicacionRequestDTO dto) {
        return mapper.map(dto, EstadisticasPublicacion.class);
    }

    private EstadisticasPublicacionResponseDTO aDto(EstadisticasPublicacion entidad) {
        return mapper.map(entidad, EstadisticasPublicacionResponseDTO.class);
    }
}
