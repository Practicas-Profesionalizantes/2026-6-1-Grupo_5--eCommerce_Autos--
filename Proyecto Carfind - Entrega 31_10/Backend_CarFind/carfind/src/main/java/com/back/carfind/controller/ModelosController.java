package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ModelosRequestDTO;
import com.back.carfind.dtos.ModelosResponseDTO;
import com.back.carfind.model.Modelos;
import com.back.carfind.service.ModelosService;

@RestController
@RequestMapping("/api/modelos")
@CrossOrigin(origins = "*")
public class ModelosController {

    private final ModelosService service;
    private final ModelMapper mapper;

    public ModelosController(ModelosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ModelosResponseDTO crearModelo(@RequestBody ModelosRequestDTO dto) {
        return aDto(service.crearModelo(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ModelosResponseDTO obtenerModelo(@PathVariable Long id) {
        return aDto(service.obtenerModelo(id));
    }

    @GetMapping
    public List<ModelosResponseDTO> listarModelos() {
        return service.listarModelos().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ModelosResponseDTO actualizarModelo(@PathVariable Long id, @RequestBody ModelosRequestDTO dto) {
        return aDto(service.actualizarModelo(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarModelo(@PathVariable Long id) {
        service.eliminarModelo(id);
    }

    private Modelos aEntidad(ModelosRequestDTO dto) {
        return mapper.map(dto, Modelos.class);
    }

    private ModelosResponseDTO aDto(Modelos entidad) {
        return mapper.map(entidad, ModelosResponseDTO.class);
    }
}
