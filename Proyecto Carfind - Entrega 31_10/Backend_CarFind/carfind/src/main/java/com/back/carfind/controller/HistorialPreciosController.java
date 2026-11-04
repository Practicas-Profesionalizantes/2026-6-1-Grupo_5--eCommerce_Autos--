package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.HistorialPreciosRequestDTO;
import com.back.carfind.dtos.HistorialPreciosResponseDTO;
import com.back.carfind.model.HistorialPrecios;
import com.back.carfind.service.HistorialPreciosService;

@RestController
@RequestMapping("/api/historial-precios")
@CrossOrigin(origins = "*")
public class HistorialPreciosController {

    private final HistorialPreciosService service;
    private final ModelMapper mapper;

    public HistorialPreciosController(HistorialPreciosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public HistorialPreciosResponseDTO crearHistorialPrecio(@RequestBody HistorialPreciosRequestDTO dto) {
        return aDto(service.crearHistorialPrecio(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public HistorialPreciosResponseDTO obtenerHistorialPrecio(@PathVariable Long id) {
        return aDto(service.obtenerHistorialPrecio(id));
    }

    @GetMapping
    public List<HistorialPreciosResponseDTO> listarHistorialPrecios() {
        return service.listarHistorialPrecios().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public HistorialPreciosResponseDTO actualizarHistorialPrecio(@PathVariable Long id, @RequestBody HistorialPreciosRequestDTO dto) {
        return aDto(service.actualizarHistorialPrecio(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorialPrecio(@PathVariable Long id) {
        service.eliminarHistorialPrecio(id);
    }

    private HistorialPrecios aEntidad(HistorialPreciosRequestDTO dto) {
        return mapper.map(dto, HistorialPrecios.class);
    }

    private HistorialPreciosResponseDTO aDto(HistorialPrecios entidad) {
        return mapper.map(entidad, HistorialPreciosResponseDTO.class);
    }
}
