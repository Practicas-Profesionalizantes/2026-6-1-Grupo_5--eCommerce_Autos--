package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.MarcasRequestDTO;
import com.back.carfind.dtos.MarcasResponseDTO;
import com.back.carfind.model.Marcas;
import com.back.carfind.service.MarcasService;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*")
public class MarcasController {

    private final MarcasService service;
    private final ModelMapper mapper;

    public MarcasController(MarcasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public MarcasResponseDTO crearMarca(@RequestBody MarcasRequestDTO dto) {
        return aDto(service.crearMarca(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public MarcasResponseDTO obtenerMarca(@PathVariable Long id) {
        return aDto(service.obtenerMarca(id));
    }

    @GetMapping
    public List<MarcasResponseDTO> listarMarcas() {
        return service.listarMarcas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public MarcasResponseDTO actualizarMarca(@PathVariable Long id, @RequestBody MarcasRequestDTO dto) {
        return aDto(service.actualizarMarca(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarMarca(@PathVariable Long id) {
        service.eliminarMarca(id);
    }

    private Marcas aEntidad(MarcasRequestDTO dto) {
        return mapper.map(dto, Marcas.class);
    }

    private MarcasResponseDTO aDto(Marcas entidad) {
        return mapper.map(entidad, MarcasResponseDTO.class);
    }
}
