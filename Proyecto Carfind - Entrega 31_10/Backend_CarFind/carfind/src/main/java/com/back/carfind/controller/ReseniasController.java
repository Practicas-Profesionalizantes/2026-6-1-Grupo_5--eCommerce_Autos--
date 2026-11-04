package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ReseniasRequestDTO;
import com.back.carfind.dtos.ReseniasResponseDTO;
import com.back.carfind.model.Resenias;
import com.back.carfind.service.ReseniasService;

@RestController
@RequestMapping("/api/resenias")
@CrossOrigin(origins = "*")
public class ReseniasController {

    private final ReseniasService service;
    private final ModelMapper mapper;

    public ReseniasController(ReseniasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ReseniasResponseDTO crearResenia(@RequestBody ReseniasRequestDTO dto) {
        return aDto(service.crearResenia(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ReseniasResponseDTO obtenerResenia(@PathVariable Long id) {
        return aDto(service.obtenerResenia(id));
    }

    @GetMapping
    public List<ReseniasResponseDTO> listarResenias() {
        return service.listarResenias().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ReseniasResponseDTO actualizarResenia(@PathVariable Long id, @RequestBody ReseniasRequestDTO dto) {
        return aDto(service.actualizarResenia(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarResenia(@PathVariable Long id) {
        service.eliminarResenia(id);
    }

    private Resenias aEntidad(ReseniasRequestDTO dto) {
        return mapper.map(dto, Resenias.class);
    }

    private ReseniasResponseDTO aDto(Resenias entidad) {
        return mapper.map(entidad, ReseniasResponseDTO.class);
    }
}
