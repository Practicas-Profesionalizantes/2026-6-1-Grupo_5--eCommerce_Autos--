package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.RolesRequestDTO;
import com.back.carfind.dtos.RolesResponseDTO;
import com.back.carfind.model.Roles;
import com.back.carfind.service.RolesService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolesController {

    private final RolesService service;
    private final ModelMapper mapper;

    public RolesController(RolesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public RolesResponseDTO crearRol(@RequestBody RolesRequestDTO dto) {
        return aDto(service.crearRol(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public RolesResponseDTO obtenerRol(@PathVariable Long id) {
        return aDto(service.obtenerRol(id));
    }

    @GetMapping
    public List<RolesResponseDTO> listarRoles() {
        return service.listarRoles().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public RolesResponseDTO actualizarRol(@PathVariable Long id, @RequestBody RolesRequestDTO dto) {
        return aDto(service.actualizarRol(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Long id) {
        service.eliminarRol(id);
    }

    private Roles aEntidad(RolesRequestDTO dto) {
        return mapper.map(dto, Roles.class);
    }

    private RolesResponseDTO aDto(Roles entidad) {
        return mapper.map(entidad, RolesResponseDTO.class);
    }
}
