package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.CuotasRequestDTO;
import com.back.carfind.dtos.CuotasResponseDTO;
import com.back.carfind.model.Cuotas;
import com.back.carfind.service.CuotasService;

@RestController
@RequestMapping("/api/cuotas")
@CrossOrigin(origins = "*")
public class CuotasController {

    private final CuotasService service;
    private final ModelMapper mapper;

    public CuotasController(CuotasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public CuotasResponseDTO crearCuota(@RequestBody CuotasRequestDTO dto) {
        return aDto(service.crearCuota(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public CuotasResponseDTO obtenerCuota(@PathVariable Long id) {
        return aDto(service.obtenerCuota(id));
    }

    @GetMapping
    public List<CuotasResponseDTO> listarCuotas() {
        return service.listarCuotas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public CuotasResponseDTO actualizarCuota(@PathVariable Long id, @RequestBody CuotasRequestDTO dto) {
        return aDto(service.actualizarCuota(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarCuota(@PathVariable Long id) {
        service.eliminarCuota(id);
    }

    private Cuotas aEntidad(CuotasRequestDTO dto) {
        return mapper.map(dto, Cuotas.class);
    }

    private CuotasResponseDTO aDto(Cuotas entidad) {
        return mapper.map(entidad, CuotasResponseDTO.class);
    }
}
