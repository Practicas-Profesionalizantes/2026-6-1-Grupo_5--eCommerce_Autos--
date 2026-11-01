package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.BloqueosRequestDTO;
import com.back.carfind.dtos.BloqueosResponseDTO;
import com.back.carfind.model.Bloqueos;
import com.back.carfind.service.BloqueosService;

@RestController
@RequestMapping("/api/bloqueos")
@CrossOrigin(origins = "*")
public class BloqueosController {

    private final BloqueosService service;
    private final ModelMapper mapper;

    public BloqueosController(BloqueosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public BloqueosResponseDTO crearBloqueo(@RequestBody BloqueosRequestDTO dto) {
        return aDto(service.crearBloqueo(aEntidad(dto)));
    }

    @GetMapping("/{usuarioId}/{usuarioBloqueadoId}")
    public BloqueosResponseDTO obtenerBloqueo(@PathVariable Long usuarioId, @PathVariable Long usuarioBloqueadoId) {
        return aDto(service.obtenerBloqueo(usuarioId, usuarioBloqueadoId));
    }

    @GetMapping
    public List<BloqueosResponseDTO> listarBloqueos() {
        return service.listarBloqueos().stream().map(this::aDto).toList();
    }

    @DeleteMapping("/{usuarioId}/{usuarioBloqueadoId}")
    public void eliminarBloqueo(@PathVariable Long usuarioId, @PathVariable Long usuarioBloqueadoId) {
        service.eliminarBloqueo(usuarioId, usuarioBloqueadoId);
    }

    private Bloqueos aEntidad(BloqueosRequestDTO dto) {
        return mapper.map(dto, Bloqueos.class);
    }

    private BloqueosResponseDTO aDto(Bloqueos entidad) {
        return mapper.map(entidad, BloqueosResponseDTO.class);
    }
}
