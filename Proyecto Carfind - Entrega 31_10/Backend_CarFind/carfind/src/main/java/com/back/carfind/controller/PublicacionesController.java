package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PublicacionesRequestDTO;
import com.back.carfind.dtos.PublicacionesResponseDTO;
import com.back.carfind.model.Publicaciones;
import com.back.carfind.service.PublicacionesService;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionesController {

    private final PublicacionesService service;
    private final ModelMapper mapper;

    public PublicacionesController(PublicacionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PublicacionesResponseDTO crearPublicacion(@RequestBody PublicacionesRequestDTO dto) {
        return aDto(service.crearPublicacion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PublicacionesResponseDTO obtenerPublicacion(@PathVariable Long id) {
        return aDto(service.obtenerPublicacion(id));
    }

    @GetMapping
    public List<PublicacionesResponseDTO> listarPublicaciones() {
        return service.listarPublicaciones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PublicacionesResponseDTO actualizarPublicacion(@PathVariable Long id, @RequestBody PublicacionesRequestDTO dto) {
        return aDto(service.actualizarPublicacion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPublicacion(@PathVariable Long id) {
        service.eliminarPublicacion(id);
    }

    private Publicaciones aEntidad(PublicacionesRequestDTO dto) {
        return mapper.map(dto, Publicaciones.class);
    }

    private PublicacionesResponseDTO aDto(Publicaciones entidad) {
        return mapper.map(entidad, PublicacionesResponseDTO.class);
    }
}
