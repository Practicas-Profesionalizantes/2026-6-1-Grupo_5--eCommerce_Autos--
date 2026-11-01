package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.back.carfind.dtos.UsuarioRequestDTO;
import com.back.carfind.dtos.UsuarioResponseDTO;
import com.back.carfind.model.Usuario;
import com.back.carfind.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioService service, ModelMapper mapper, PasswordEncoder encoder) {
        this.service = service;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @PostMapping
    public UsuarioResponseDTO crearUsuario(@RequestBody UsuarioRequestDTO dto) {
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña es obligatoria");
        }
        Usuario usuario = aEntidad(dto);
        usuario.setPasswordHash(encoder.encode(dto.getPassword()));
        return aDto(service.crearUsuario(usuario));
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerUsuario(@PathVariable Long id) {
        return aDto(service.obtenerUsuario(id));
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return service.listarUsuarios().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = aEntidad(dto);
        // Si no se manda una contraseña nueva, se conserva la actual
        usuario.setPasswordHash(dto.getPassword() == null || dto.getPassword().isBlank()
                ? service.obtenerUsuario(id).getPasswordHash()
                : encoder.encode(dto.getPassword()));
        return aDto(service.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
    }

    private Usuario aEntidad(UsuarioRequestDTO dto) {
        return mapper.map(dto, Usuario.class);
    }

    private UsuarioResponseDTO aDto(Usuario entidad) {
        return mapper.map(entidad, UsuarioResponseDTO.class);
    }
}
