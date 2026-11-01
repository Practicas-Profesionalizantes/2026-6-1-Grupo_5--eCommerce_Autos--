package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.MultimediaRequestDTO;
import com.back.carfind.dtos.MultimediaResponseDTO;
import com.back.carfind.model.Multimedia;
import com.back.carfind.service.MultimediaService;

@RestController
@RequestMapping("/api/multimedia")
@CrossOrigin(origins = "*")
public class MultimediaController {

    private final MultimediaService service;
    private final ModelMapper mapper;

    public MultimediaController(MultimediaService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public MultimediaResponseDTO crearMultimedia(@RequestBody MultimediaRequestDTO dto) {
        return aDto(service.crearMultimedia(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public MultimediaResponseDTO obtenerMultimedia(@PathVariable Long id) {
        return aDto(service.obtenerMultimedia(id));
    }

    @GetMapping
    public List<MultimediaResponseDTO> listarMultimedia() {
        return service.listarMultimedia().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public MultimediaResponseDTO actualizarMultimedia(@PathVariable Long id, @RequestBody MultimediaRequestDTO dto) {
        return aDto(service.actualizarMultimedia(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarMultimedia(@PathVariable Long id) {
        service.eliminarMultimedia(id);
    }

    private Multimedia aEntidad(MultimediaRequestDTO dto) {
        return mapper.map(dto, Multimedia.class);
    }

    private MultimediaResponseDTO aDto(Multimedia entidad) {
        return mapper.map(entidad, MultimediaResponseDTO.class);
    }
}
