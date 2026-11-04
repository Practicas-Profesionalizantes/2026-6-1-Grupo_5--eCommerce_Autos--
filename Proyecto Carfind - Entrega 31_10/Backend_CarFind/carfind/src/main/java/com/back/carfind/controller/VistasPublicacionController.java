package com.back.carfind.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.VistasPublicacionRequestDTO;
import com.back.carfind.dtos.VistasPublicacionResponseDTO;
import com.back.carfind.model.VistasPublicacion;
import com.back.carfind.service.VistasPublicacionService;

@RestController
@RequestMapping("/api/vistas-publicacion")
@CrossOrigin(origins = "*")
public class VistasPublicacionController {

    private final VistasPublicacionService service;
    private final ModelMapper mapper;

    public VistasPublicacionController(VistasPublicacionService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public VistasPublicacionResponseDTO crearVistaPublicacion(@RequestBody VistasPublicacionRequestDTO dto) {
        return aDto(service.crearVistaPublicacion(aEntidad(dto)));
    }

    @GetMapping("/{usuarioId}/{publicacionId}")
    public VistasPublicacionResponseDTO obtenerVistaPublicacion(@PathVariable Long usuarioId, @PathVariable Long publicacionId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return aDto(service.obtenerVistaPublicacion(usuarioId, publicacionId, fecha));
    }

    @GetMapping
    public List<VistasPublicacionResponseDTO> listarVistasPublicacion() {
        return service.listarVistasPublicacion().stream().map(this::aDto).toList();
    }

    @DeleteMapping("/{usuarioId}/{publicacionId}")
    public void eliminarVistaPublicacion(@PathVariable Long usuarioId, @PathVariable Long publicacionId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        service.eliminarVistaPublicacion(usuarioId, publicacionId, fecha);
    }

    private VistasPublicacion aEntidad(VistasPublicacionRequestDTO dto) {
        return mapper.map(dto, VistasPublicacion.class);
    }

    private VistasPublicacionResponseDTO aDto(VistasPublicacion entidad) {
        return mapper.map(entidad, VistasPublicacionResponseDTO.class);
    }
}
