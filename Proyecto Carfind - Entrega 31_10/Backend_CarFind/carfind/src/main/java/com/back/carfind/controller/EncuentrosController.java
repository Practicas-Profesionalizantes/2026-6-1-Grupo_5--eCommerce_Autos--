package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.EncuentrosRequestDTO;
import com.back.carfind.dtos.EncuentrosResponseDTO;
import com.back.carfind.model.Encuentros;
import com.back.carfind.service.EncuentrosService;

@RestController
@RequestMapping("/api/encuentros")
@CrossOrigin(origins = "*")
public class EncuentrosController {

    private final EncuentrosService service;
    private final ModelMapper mapper;

    public EncuentrosController(EncuentrosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public EncuentrosResponseDTO crearEncuentro(@RequestBody EncuentrosRequestDTO dto) {
        return aDto(service.crearEncuentro(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public EncuentrosResponseDTO obtenerEncuentro(@PathVariable Long id) {
        return aDto(service.obtenerEncuentro(id));
    }

    @GetMapping
    public List<EncuentrosResponseDTO> listarEncuentros() {
        return service.listarEncuentros().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public EncuentrosResponseDTO actualizarEncuentro(@PathVariable Long id, @RequestBody EncuentrosRequestDTO dto) {
        return aDto(service.actualizarEncuentro(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarEncuentro(@PathVariable Long id) {
        service.eliminarEncuentro(id);
    }

    private Encuentros aEntidad(EncuentrosRequestDTO dto) {
        return mapper.map(dto, Encuentros.class);
    }

    private EncuentrosResponseDTO aDto(Encuentros entidad) {
        return mapper.map(entidad, EncuentrosResponseDTO.class);
    }
}
