package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.MovimientosRequestDTO;
import com.back.carfind.dtos.MovimientosResponseDTO;
import com.back.carfind.model.Movimientos;
import com.back.carfind.service.MovimientosService;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientosController {

    private final MovimientosService service;
    private final ModelMapper mapper;

    public MovimientosController(MovimientosService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public MovimientosResponseDTO crearMovimiento(@RequestBody MovimientosRequestDTO dto) {
        return aDto(service.crearMovimiento(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public MovimientosResponseDTO obtenerMovimiento(@PathVariable Long id) {
        return aDto(service.obtenerMovimiento(id));
    }

    @GetMapping
    public List<MovimientosResponseDTO> listarMovimientos() {
        return service.listarMovimientos().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public MovimientosResponseDTO actualizarMovimiento(@PathVariable Long id, @RequestBody MovimientosRequestDTO dto) {
        return aDto(service.actualizarMovimiento(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarMovimiento(@PathVariable Long id) {
        service.eliminarMovimiento(id);
    }

    private Movimientos aEntidad(MovimientosRequestDTO dto) {
        return mapper.map(dto, Movimientos.class);
    }

    private MovimientosResponseDTO aDto(Movimientos entidad) {
        return mapper.map(entidad, MovimientosResponseDTO.class);
    }
}
