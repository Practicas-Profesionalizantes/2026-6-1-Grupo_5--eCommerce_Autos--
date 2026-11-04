package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.SesionesRequestDTO;
import com.back.carfind.dtos.SesionesResponseDTO;
import com.back.carfind.model.Sesiones;
import com.back.carfind.service.SesionesService;

@RestController
@RequestMapping("/api/sesiones")
@CrossOrigin(origins = "*")
public class SesionesController {

    private final SesionesService service;
    private final ModelMapper mapper;

    public SesionesController(SesionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public SesionesResponseDTO crearSesion(@RequestBody SesionesRequestDTO dto) {
        return aDto(service.crearSesion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public SesionesResponseDTO obtenerSesion(@PathVariable Long id) {
        return aDto(service.obtenerSesion(id));
    }

    @GetMapping
    public List<SesionesResponseDTO> listarSesiones() {
        return service.listarSesiones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public SesionesResponseDTO actualizarSesion(@PathVariable Long id, @RequestBody SesionesRequestDTO dto) {
        return aDto(service.actualizarSesion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarSesion(@PathVariable Long id) {
        service.eliminarSesion(id);
    }

    private Sesiones aEntidad(SesionesRequestDTO dto) {
        return mapper.map(dto, Sesiones.class);
    }

    private SesionesResponseDTO aDto(Sesiones entidad) {
        return mapper.map(entidad, SesionesResponseDTO.class);
    }
}
