package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PlanesCuotasRequestDTO;
import com.back.carfind.dtos.PlanesCuotasResponseDTO;
import com.back.carfind.model.PlanesCuotas;
import com.back.carfind.service.PlanesCuotasService;

@RestController
@RequestMapping("/api/planes-cuotas")
@CrossOrigin(origins = "*")
public class PlanesCuotasController {

    private final PlanesCuotasService service;
    private final ModelMapper mapper;

    public PlanesCuotasController(PlanesCuotasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PlanesCuotasResponseDTO crearPlanCuotas(@RequestBody PlanesCuotasRequestDTO dto) {
        return aDto(service.crearPlanCuotas(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PlanesCuotasResponseDTO obtenerPlanCuotas(@PathVariable Long id) {
        return aDto(service.obtenerPlanCuotas(id));
    }

    @GetMapping
    public List<PlanesCuotasResponseDTO> listarPlanesCuotas() {
        return service.listarPlanesCuotas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PlanesCuotasResponseDTO actualizarPlanCuotas(@PathVariable Long id, @RequestBody PlanesCuotasRequestDTO dto) {
        return aDto(service.actualizarPlanCuotas(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanCuotas(@PathVariable Long id) {
        service.eliminarPlanCuotas(id);
    }

    private PlanesCuotas aEntidad(PlanesCuotasRequestDTO dto) {
        return mapper.map(dto, PlanesCuotas.class);
    }

    private PlanesCuotasResponseDTO aDto(PlanesCuotas entidad) {
        return mapper.map(entidad, PlanesCuotasResponseDTO.class);
    }
}
