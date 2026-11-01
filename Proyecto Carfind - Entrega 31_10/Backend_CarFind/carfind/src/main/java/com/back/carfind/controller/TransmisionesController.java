package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.TransmisionesRequestDTO;
import com.back.carfind.dtos.TransmisionesResponseDTO;
import com.back.carfind.model.Transmisiones;
import com.back.carfind.service.TransmisionesService;

@RestController
@RequestMapping("/api/transmisiones")
@CrossOrigin(origins = "*")
public class TransmisionesController {

    private final TransmisionesService service;
    private final ModelMapper mapper;

    public TransmisionesController(TransmisionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TransmisionesResponseDTO crearTransmision(@RequestBody TransmisionesRequestDTO dto) {
        return aDto(service.crearTransmision(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public TransmisionesResponseDTO obtenerTransmision(@PathVariable Long id) {
        return aDto(service.obtenerTransmision(id));
    }

    @GetMapping
    public List<TransmisionesResponseDTO> listarTransmisiones() {
        return service.listarTransmisiones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public TransmisionesResponseDTO actualizarTransmision(@PathVariable Long id, @RequestBody TransmisionesRequestDTO dto) {
        return aDto(service.actualizarTransmision(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarTransmision(@PathVariable Long id) {
        service.eliminarTransmision(id);
    }

    private Transmisiones aEntidad(TransmisionesRequestDTO dto) {
        return mapper.map(dto, Transmisiones.class);
    }

    private TransmisionesResponseDTO aDto(Transmisiones entidad) {
        return mapper.map(entidad, TransmisionesResponseDTO.class);
    }
}
