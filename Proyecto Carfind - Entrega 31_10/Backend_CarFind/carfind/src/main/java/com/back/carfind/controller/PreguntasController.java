package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PreguntasRequestDTO;
import com.back.carfind.dtos.PreguntasResponseDTO;
import com.back.carfind.model.Preguntas;
import com.back.carfind.service.PreguntasService;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin(origins = "*")
public class PreguntasController {

    private final PreguntasService service;
    private final ModelMapper mapper;

    public PreguntasController(PreguntasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PreguntasResponseDTO crearPregunta(@RequestBody PreguntasRequestDTO dto) {
        return aDto(service.crearPregunta(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PreguntasResponseDTO obtenerPregunta(@PathVariable Long id) {
        return aDto(service.obtenerPregunta(id));
    }

    @GetMapping
    public List<PreguntasResponseDTO> listarPreguntas() {
        return service.listarPreguntas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PreguntasResponseDTO actualizarPregunta(@PathVariable Long id, @RequestBody PreguntasRequestDTO dto) {
        return aDto(service.actualizarPregunta(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPregunta(@PathVariable Long id) {
        service.eliminarPregunta(id);
    }

    private Preguntas aEntidad(PreguntasRequestDTO dto) {
        return mapper.map(dto, Preguntas.class);
    }

    private PreguntasResponseDTO aDto(Preguntas entidad) {
        return mapper.map(entidad, PreguntasResponseDTO.class);
    }
}
