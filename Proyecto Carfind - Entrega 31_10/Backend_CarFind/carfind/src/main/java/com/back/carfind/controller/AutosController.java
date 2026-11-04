package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.AutosRequestDTO;
import com.back.carfind.dtos.AutosResponseDTO;
import com.back.carfind.model.Autos;
import com.back.carfind.service.AutosService;

@RestController
@RequestMapping("/api/autos")
@CrossOrigin(origins = "*")
public class AutosController {

    private final AutosService service;
    private final ModelMapper mapper;

    public AutosController(AutosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public AutosResponseDTO crearAuto(@RequestBody AutosRequestDTO dto) {
        return aDto(service.crearAuto(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public AutosResponseDTO obtenerAuto(@PathVariable Long id) {
        return aDto(service.obtenerAuto(id));
    }

    @GetMapping
    public List<AutosResponseDTO> listarAutos() {
        return service.listarAutos().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public AutosResponseDTO actualizarAuto(@PathVariable Long id, @RequestBody AutosRequestDTO dto) {
        return aDto(service.actualizarAuto(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarAuto(@PathVariable Long id) {
        service.eliminarAuto(id);
    }

    private Autos aEntidad(AutosRequestDTO dto) {
        return mapper.map(dto, Autos.class);
    }

    private AutosResponseDTO aDto(Autos entidad) {
        return mapper.map(entidad, AutosResponseDTO.class);
    }
}
