package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.TarjetasUsuarioRequestDTO;
import com.back.carfind.dtos.TarjetasUsuarioResponseDTO;
import com.back.carfind.model.TarjetasUsuario;
import com.back.carfind.service.TarjetasUsuarioService;

@RestController
@RequestMapping("/api/tarjetas-usuario")
@CrossOrigin(origins = "*")
public class TarjetasUsuarioController {

    private final TarjetasUsuarioService service;
    private final ModelMapper mapper;

    public TarjetasUsuarioController(TarjetasUsuarioService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TarjetasUsuarioResponseDTO crearTarjetaUsuario(@RequestBody TarjetasUsuarioRequestDTO dto) {
        return aDto(service.crearTarjetaUsuario(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public TarjetasUsuarioResponseDTO obtenerTarjetaUsuario(@PathVariable Long id) {
        return aDto(service.obtenerTarjetaUsuario(id));
    }

    @GetMapping
    public List<TarjetasUsuarioResponseDTO> listarTarjetasUsuario() {
        return service.listarTarjetasUsuario().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public TarjetasUsuarioResponseDTO actualizarTarjetaUsuario(@PathVariable Long id, @RequestBody TarjetasUsuarioRequestDTO dto) {
        return aDto(service.actualizarTarjetaUsuario(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarTarjetaUsuario(@PathVariable Long id) {
        service.eliminarTarjetaUsuario(id);
    }

    private TarjetasUsuario aEntidad(TarjetasUsuarioRequestDTO dto) {
        return mapper.map(dto, TarjetasUsuario.class);
    }

    private TarjetasUsuarioResponseDTO aDto(TarjetasUsuario entidad) {
        return mapper.map(entidad, TarjetasUsuarioResponseDTO.class);
    }
}
