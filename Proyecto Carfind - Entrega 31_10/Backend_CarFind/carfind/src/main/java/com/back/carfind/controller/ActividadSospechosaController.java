package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ActividadSospechosaRequestDTO;
import com.back.carfind.dtos.ActividadSospechosaResponseDTO;
import com.back.carfind.model.ActividadSospechosa;
import com.back.carfind.service.ActividadSospechosaService;

@RestController
@RequestMapping("/api/actividades-sospechosas")
@CrossOrigin(origins = "*")
public class ActividadSospechosaController {

    private final ActividadSospechosaService service;
    private final ModelMapper mapper;

    public ActividadSospechosaController(ActividadSospechosaService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ActividadSospechosaResponseDTO crearActividadSospechosa(@RequestBody ActividadSospechosaRequestDTO dto) {
        return aDto(service.crearActividadSospechosa(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ActividadSospechosaResponseDTO obtenerActividadSospechosa(@PathVariable Long id) {
        return aDto(service.obtenerActividadSospechosa(id));
    }

    @GetMapping
    public List<ActividadSospechosaResponseDTO> listarActividadesSospechosas() {
        return service.listarActividadesSospechosas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ActividadSospechosaResponseDTO actualizarActividadSospechosa(@PathVariable Long id, @RequestBody ActividadSospechosaRequestDTO dto) {
        return aDto(service.actualizarActividadSospechosa(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarActividadSospechosa(@PathVariable Long id) {
        service.eliminarActividadSospechosa(id);
    }

    private ActividadSospechosa aEntidad(ActividadSospechosaRequestDTO dto) {
        return mapper.map(dto, ActividadSospechosa.class);
    }

    private ActividadSospechosaResponseDTO aDto(ActividadSospechosa entidad) {
        return mapper.map(entidad, ActividadSospechosaResponseDTO.class);
    }
}
