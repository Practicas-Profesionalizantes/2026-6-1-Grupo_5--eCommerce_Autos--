package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.NotificacionesRequestDTO;
import com.back.carfind.dtos.NotificacionesResponseDTO;
import com.back.carfind.model.Notificaciones;
import com.back.carfind.service.NotificacionesService;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionesController {

    private final NotificacionesService service;
    private final ModelMapper mapper;

    public NotificacionesController(NotificacionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public NotificacionesResponseDTO crearNotificacion(@RequestBody NotificacionesRequestDTO dto) {
        return aDto(service.crearNotificacion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public NotificacionesResponseDTO obtenerNotificacion(@PathVariable Long id) {
        return aDto(service.obtenerNotificacion(id));
    }

    @GetMapping
    public List<NotificacionesResponseDTO> listarNotificaciones() {
        return service.listarNotificaciones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public NotificacionesResponseDTO actualizarNotificacion(@PathVariable Long id, @RequestBody NotificacionesRequestDTO dto) {
        return aDto(service.actualizarNotificacion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarNotificacion(@PathVariable Long id) {
        service.eliminarNotificacion(id);
    }

    private Notificaciones aEntidad(NotificacionesRequestDTO dto) {
        return mapper.map(dto, Notificaciones.class);
    }

    private NotificacionesResponseDTO aDto(Notificaciones entidad) {
        return mapper.map(entidad, NotificacionesResponseDTO.class);
    }
}
