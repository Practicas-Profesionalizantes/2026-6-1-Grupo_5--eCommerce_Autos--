package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.ActividadUsuarioRequestDTO;
import com.back.carfind.dtos.ActividadUsuarioResponseDTO;
import com.back.carfind.model.ActividadUsuario;
import com.back.carfind.service.ActividadUsuarioService;

@RestController
@RequestMapping("/api/actividades-usuarios")
@CrossOrigin(origins = "*")
public class ActividadUsuarioController {

    private final ActividadUsuarioService service;
    private final ModelMapper mapper;

    public ActividadUsuarioController(ActividadUsuarioService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ActividadUsuarioResponseDTO crearActividadUsuario(@RequestBody ActividadUsuarioRequestDTO dto) {
        return aDto(service.crearActividadUsuario(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public ActividadUsuarioResponseDTO obtenerActividadUsuario(@PathVariable Long id) {
        return aDto(service.obtenerActividadUsuario(id));
    }

    @GetMapping
    public List<ActividadUsuarioResponseDTO> listarActividadesUsuario() {
        return service.listarActividadesUsuario().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public ActividadUsuarioResponseDTO actualizarActividadUsuario(@PathVariable Long id, @RequestBody ActividadUsuarioRequestDTO dto) {
        return aDto(service.actualizarActividadUsuario(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarActividadUsuario(@PathVariable Long id) {
        service.eliminarActividadUsuario(id);
    }

    private ActividadUsuario aEntidad(ActividadUsuarioRequestDTO dto) {
        return mapper.map(dto, ActividadUsuario.class);
    }

    private ActividadUsuarioResponseDTO aDto(ActividadUsuario entidad) {
        return mapper.map(entidad, ActividadUsuarioResponseDTO.class);
    }
}
