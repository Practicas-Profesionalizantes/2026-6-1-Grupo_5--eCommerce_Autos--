package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.DispositivosUsuarioRequestDTO;
import com.back.carfind.dtos.DispositivosUsuarioResponseDTO;
import com.back.carfind.model.DispositivosUsuario;
import com.back.carfind.service.DispositivosUsuarioService;

@RestController
@RequestMapping("/api/dispositivos-usuario")
@CrossOrigin(origins = "*")
public class DispositivosUsuarioController {

    private final DispositivosUsuarioService service;
    private final ModelMapper mapper;

    public DispositivosUsuarioController(DispositivosUsuarioService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public DispositivosUsuarioResponseDTO crearDispositivoUsuario(@RequestBody DispositivosUsuarioRequestDTO dto) {
        return aDto(service.crearDispositivoUsuario(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public DispositivosUsuarioResponseDTO obtenerDispositivoUsuario(@PathVariable Long id) {
        return aDto(service.obtenerDispositivoUsuario(id));
    }

    @GetMapping
    public List<DispositivosUsuarioResponseDTO> listarDispositivosUsuario() {
        return service.listarDispositivosUsuario().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public DispositivosUsuarioResponseDTO actualizarDispositivoUsuario(@PathVariable Long id, @RequestBody DispositivosUsuarioRequestDTO dto) {
        return aDto(service.actualizarDispositivoUsuario(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarDispositivoUsuario(@PathVariable Long id) {
        service.eliminarDispositivoUsuario(id);
    }

    private DispositivosUsuario aEntidad(DispositivosUsuarioRequestDTO dto) {
        return mapper.map(dto, DispositivosUsuario.class);
    }

    private DispositivosUsuarioResponseDTO aDto(DispositivosUsuario entidad) {
        return mapper.map(entidad, DispositivosUsuarioResponseDTO.class);
    }
}
