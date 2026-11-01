package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.FavoritosRequestDTO;
import com.back.carfind.dtos.FavoritosResponseDTO;
import com.back.carfind.model.Favoritos;
import com.back.carfind.service.FavoritosService;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritosController {

    private final FavoritosService service;
    private final ModelMapper mapper;

    public FavoritosController(FavoritosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public FavoritosResponseDTO crearFavorito(@RequestBody FavoritosRequestDTO dto) {
        return aDto(service.crearFavorito(aEntidad(dto)));
    }

    @GetMapping("/{usuarioId}/{publicacionId}")
    public FavoritosResponseDTO obtenerFavorito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        return aDto(service.obtenerFavorito(usuarioId, publicacionId));
    }

    @GetMapping
    public List<FavoritosResponseDTO> listarFavoritos() {
        return service.listarFavoritos().stream().map(this::aDto).toList();
    }

    @DeleteMapping("/{usuarioId}/{publicacionId}")
    public void eliminarFavorito(@PathVariable Long usuarioId, @PathVariable Long publicacionId) {
        service.eliminarFavorito(usuarioId, publicacionId);
    }

    private Favoritos aEntidad(FavoritosRequestDTO dto) {
        return mapper.map(dto, Favoritos.class);
    }

    private FavoritosResponseDTO aDto(Favoritos entidad) {
        return mapper.map(entidad, FavoritosResponseDTO.class);
    }
}
