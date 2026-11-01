package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ModeracionMultimediaRequestDTO;
import com.back.carfind.dtos.ModeracionMultimediaResponseDTO;
import com.back.carfind.model.ModeracionMultimedia;
import com.back.carfind.service.ModeracionMultimediaService;

@RestController
@RequestMapping("/api/moderacion-multimedia")
@CrossOrigin(origins = "*")
public class ModeracionMultimediaController {

    private final ModeracionMultimediaService service;
    private final ModelMapper mapper;

    public ModeracionMultimediaController(ModeracionMultimediaService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ModeracionMultimediaResponseDTO crearModeracionMultimedia(@RequestBody ModeracionMultimediaRequestDTO dto) {
        return aDto(service.crearModeracionMultimedia(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ModeracionMultimediaResponseDTO obtenerModeracionMultimedia(@PathVariable Long id) {
        return aDto(service.obtenerModeracionMultimedia(id));
    }

    @GetMapping
    public List<ModeracionMultimediaResponseDTO> listarModeracionesMultimedia() {
        return service.listarModeracionesMultimedia().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ModeracionMultimediaResponseDTO actualizarModeracionMultimedia(@PathVariable Long id, @RequestBody ModeracionMultimediaRequestDTO dto) {
        return aDto(service.actualizarModeracionMultimedia(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarModeracionMultimedia(@PathVariable Long id) {
        service.eliminarModeracionMultimedia(id);
    }

    private ModeracionMultimedia aEntidad(ModeracionMultimediaRequestDTO dto) {
        return mapper.map(dto, ModeracionMultimedia.class);
    }

    private ModeracionMultimediaResponseDTO aDto(ModeracionMultimedia entidad) {
        return mapper.map(entidad, ModeracionMultimediaResponseDTO.class);
    }
}
