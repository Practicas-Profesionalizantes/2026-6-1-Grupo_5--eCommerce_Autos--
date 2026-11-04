package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.VisitasAutoRequestDTO;
import com.back.carfind.dtos.VisitasAutoResponseDTO;
import com.back.carfind.model.VisitasAuto;
import com.back.carfind.service.VisitasAutoService;

@RestController
@RequestMapping("/api/visitas-auto")
@CrossOrigin(origins = "*")
public class VisitasAutoController {

    private final VisitasAutoService service;
    private final ModelMapper mapper;

    public VisitasAutoController(VisitasAutoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public VisitasAutoResponseDTO crearVisitaAuto(@RequestBody VisitasAutoRequestDTO dto) {
        return aDto(service.crearVisitaAuto(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public VisitasAutoResponseDTO obtenerVisitaAuto(@PathVariable Long id) {
        return aDto(service.obtenerVisitaAuto(id));
    }

    @GetMapping
    public List<VisitasAutoResponseDTO> listarVisitasAuto() {
        return service.listarVisitasAuto().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public VisitasAutoResponseDTO actualizarVisitaAuto(@PathVariable Long id, @RequestBody VisitasAutoRequestDTO dto) {
        return aDto(service.actualizarVisitaAuto(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarVisitaAuto(@PathVariable Long id) {
        service.eliminarVisitaAuto(id);
    }

    private VisitasAuto aEntidad(VisitasAutoRequestDTO dto) {
        return mapper.map(dto, VisitasAuto.class);
    }

    private VisitasAutoResponseDTO aDto(VisitasAuto entidad) {
        return mapper.map(entidad, VisitasAutoResponseDTO.class);
    }
}
