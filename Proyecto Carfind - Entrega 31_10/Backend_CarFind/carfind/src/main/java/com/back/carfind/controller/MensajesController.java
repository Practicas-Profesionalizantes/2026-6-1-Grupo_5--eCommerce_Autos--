package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.MensajesRequestDTO;
import com.back.carfind.dtos.MensajesResponseDTO;
import com.back.carfind.model.Mensajes;
import com.back.carfind.service.MensajesService;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajesController {

    private final MensajesService service;
    private final ModelMapper mapper;

    public MensajesController(MensajesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public MensajesResponseDTO crearMensaje(@RequestBody MensajesRequestDTO dto) {
        return aDto(service.crearMensaje(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public MensajesResponseDTO obtenerMensaje(@PathVariable Long id) {
        return aDto(service.obtenerMensaje(id));
    }

    @GetMapping
    public List<MensajesResponseDTO> listarMensajes() {
        return service.listarMensajes().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public MensajesResponseDTO actualizarMensaje(@PathVariable Long id, @RequestBody MensajesRequestDTO dto) {
        return aDto(service.actualizarMensaje(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable Long id) {
        service.eliminarMensaje(id);
    }

    private Mensajes aEntidad(MensajesRequestDTO dto) {
        return mapper.map(dto, Mensajes.class);
    }

    private MensajesResponseDTO aDto(Mensajes entidad) {
        return mapper.map(entidad, MensajesResponseDTO.class);
    }
}
