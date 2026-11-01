package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.TransaccionesRequestDTO;
import com.back.carfind.dtos.TransaccionesResponseDTO;
import com.back.carfind.model.Transacciones;
import com.back.carfind.service.TransaccionesService;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionesController {

    private final TransaccionesService service;
    private final ModelMapper mapper;

    public TransaccionesController(TransaccionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TransaccionesResponseDTO crearTransaccion(@RequestBody TransaccionesRequestDTO dto) {
        return aDto(service.crearTransaccion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public TransaccionesResponseDTO obtenerTransaccion(@PathVariable Long id) {
        return aDto(service.obtenerTransaccion(id));
    }

    @GetMapping
    public List<TransaccionesResponseDTO> listarTransacciones() {
        return service.listarTransacciones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public TransaccionesResponseDTO actualizarTransaccion(@PathVariable Long id, @RequestBody TransaccionesRequestDTO dto) {
        return aDto(service.actualizarTransaccion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarTransaccion(@PathVariable Long id) {
        service.eliminarTransaccion(id);
    }

    private Transacciones aEntidad(TransaccionesRequestDTO dto) {
        return mapper.map(dto, Transacciones.class);
    }

    private TransaccionesResponseDTO aDto(Transacciones entidad) {
        return mapper.map(entidad, TransaccionesResponseDTO.class);
    }
}
