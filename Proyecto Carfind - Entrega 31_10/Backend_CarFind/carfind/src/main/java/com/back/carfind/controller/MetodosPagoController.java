package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.MetodosPagoRequestDTO;
import com.back.carfind.dtos.MetodosPagoResponseDTO;
import com.back.carfind.model.MetodosPago;
import com.back.carfind.service.MetodosPagoService;

@RestController
@RequestMapping("/api/metodos-pago")
@CrossOrigin(origins = "*")
public class MetodosPagoController {

    private final MetodosPagoService service;
    private final ModelMapper mapper;

    public MetodosPagoController(MetodosPagoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public MetodosPagoResponseDTO crearMetodoPago(@RequestBody MetodosPagoRequestDTO dto) {
        return aDto(service.crearMetodoPago(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public MetodosPagoResponseDTO obtenerMetodoPago(@PathVariable Long id) {
        return aDto(service.obtenerMetodoPago(id));
    }

    @GetMapping
    public List<MetodosPagoResponseDTO> listarMetodosPago() {
        return service.listarMetodosPago().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public MetodosPagoResponseDTO actualizarMetodoPago(@PathVariable Long id, @RequestBody MetodosPagoRequestDTO dto) {
        return aDto(service.actualizarMetodoPago(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarMetodoPago(@PathVariable Long id) {
        service.eliminarMetodoPago(id);
    }

    private MetodosPago aEntidad(MetodosPagoRequestDTO dto) {
        return mapper.map(dto, MetodosPago.class);
    }

    private MetodosPagoResponseDTO aDto(MetodosPago entidad) {
        return mapper.map(entidad, MetodosPagoResponseDTO.class);
    }
}
