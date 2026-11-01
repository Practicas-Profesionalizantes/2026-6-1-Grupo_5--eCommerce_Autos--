package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ReembolsosRequestDTO;
import com.back.carfind.dtos.ReembolsosResponseDTO;
import com.back.carfind.model.Reembolsos;
import com.back.carfind.service.ReembolsosService;

@RestController
@RequestMapping("/api/reembolsos")
@CrossOrigin(origins = "*")
public class ReembolsosController {

    private final ReembolsosService service;
    private final ModelMapper mapper;

    public ReembolsosController(ReembolsosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ReembolsosResponseDTO crearReembolso(@RequestBody ReembolsosRequestDTO dto) {
        return aDto(service.crearReembolso(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ReembolsosResponseDTO obtenerReembolso(@PathVariable Long id) {
        return aDto(service.obtenerReembolso(id));
    }

    @GetMapping
    public List<ReembolsosResponseDTO> listarReembolsos() {
        return service.listarReembolsos().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ReembolsosResponseDTO actualizarReembolso(@PathVariable Long id, @RequestBody ReembolsosRequestDTO dto) {
        return aDto(service.actualizarReembolso(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarReembolso(@PathVariable Long id) {
        service.eliminarReembolso(id);
    }

    private Reembolsos aEntidad(ReembolsosRequestDTO dto) {
        return mapper.map(dto, Reembolsos.class);
    }

    private ReembolsosResponseDTO aDto(Reembolsos entidad) {
        return mapper.map(entidad, ReembolsosResponseDTO.class);
    }
}
