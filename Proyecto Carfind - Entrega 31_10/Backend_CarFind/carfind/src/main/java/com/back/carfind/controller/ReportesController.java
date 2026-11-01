package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ReportesRequestDTO;
import com.back.carfind.dtos.ReportesResponseDTO;
import com.back.carfind.model.Reportes;
import com.back.carfind.service.ReportesService;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportesController {

    private final ReportesService service;
    private final ModelMapper mapper;

    public ReportesController(ReportesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ReportesResponseDTO crearReporte(@RequestBody ReportesRequestDTO dto) {
        return aDto(service.crearReporte(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ReportesResponseDTO obtenerReporte(@PathVariable Long id) {
        return aDto(service.obtenerReporte(id));
    }

    @GetMapping
    public List<ReportesResponseDTO> listarReportes() {
        return service.listarReportes().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ReportesResponseDTO actualizarReporte(@PathVariable Long id, @RequestBody ReportesRequestDTO dto) {
        return aDto(service.actualizarReporte(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarReporte(@PathVariable Long id) {
        service.eliminarReporte(id);
    }

    private Reportes aEntidad(ReportesRequestDTO dto) {
        return mapper.map(dto, Reportes.class);
    }

    private ReportesResponseDTO aDto(Reportes entidad) {
        return mapper.map(entidad, ReportesResponseDTO.class);
    }
}
