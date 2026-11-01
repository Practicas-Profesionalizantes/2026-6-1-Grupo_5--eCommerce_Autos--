package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.HistorialVehiculoRequestDTO;
import com.back.carfind.dtos.HistorialVehiculoResponseDTO;
import com.back.carfind.model.HistorialVehiculo;
import com.back.carfind.service.HistorialVehiculoService;

@RestController
@RequestMapping("/api/historial-vehiculos")
@CrossOrigin(origins = "*")
public class HistorialVehiculoController {

    private final HistorialVehiculoService service;
    private final ModelMapper mapper;

    public HistorialVehiculoController(HistorialVehiculoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public HistorialVehiculoResponseDTO crearHistorialVehiculo(@RequestBody HistorialVehiculoRequestDTO dto) {
        return aDto(service.crearHistorialVehiculo(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public HistorialVehiculoResponseDTO obtenerHistorialVehiculo(@PathVariable Long id) {
        return aDto(service.obtenerHistorialVehiculo(id));
    }

    @GetMapping
    public List<HistorialVehiculoResponseDTO> listarHistorialVehiculos() {
        return service.listarHistorialVehiculos().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public HistorialVehiculoResponseDTO actualizarHistorialVehiculo(@PathVariable Long id, @RequestBody HistorialVehiculoRequestDTO dto) {
        return aDto(service.actualizarHistorialVehiculo(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarHistorialVehiculo(@PathVariable Long id) {
        service.eliminarHistorialVehiculo(id);
    }

    private HistorialVehiculo aEntidad(HistorialVehiculoRequestDTO dto) {
        return mapper.map(dto, HistorialVehiculo.class);
    }

    private HistorialVehiculoResponseDTO aDto(HistorialVehiculo entidad) {
        return mapper.map(entidad, HistorialVehiculoResponseDTO.class);
    }
}
