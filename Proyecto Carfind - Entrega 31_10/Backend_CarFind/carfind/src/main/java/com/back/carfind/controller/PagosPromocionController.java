package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PagosPromocionRequestDTO;
import com.back.carfind.dtos.PagosPromocionResponseDTO;
import com.back.carfind.model.PagosPromocion;
import com.back.carfind.service.PagosPromocionService;

@RestController
@RequestMapping("/api/pagos-promocion")
@CrossOrigin(origins = "*")
public class PagosPromocionController {

    private final PagosPromocionService service;
    private final ModelMapper mapper;

    public PagosPromocionController(PagosPromocionService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PagosPromocionResponseDTO crearPagoPromocion(@RequestBody PagosPromocionRequestDTO dto) {
        return aDto(service.crearPagoPromocion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PagosPromocionResponseDTO obtenerPagoPromocion(@PathVariable Long id) {
        return aDto(service.obtenerPagoPromocion(id));
    }

    @GetMapping
    public List<PagosPromocionResponseDTO> listarPagosPromocion() {
        return service.listarPagosPromocion().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PagosPromocionResponseDTO actualizarPagoPromocion(@PathVariable Long id, @RequestBody PagosPromocionRequestDTO dto) {
        return aDto(service.actualizarPagoPromocion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPagoPromocion(@PathVariable Long id) {
        service.eliminarPagoPromocion(id);
    }

    private PagosPromocion aEntidad(PagosPromocionRequestDTO dto) {
        return mapper.map(dto, PagosPromocion.class);
    }

    private PagosPromocionResponseDTO aDto(PagosPromocion entidad) {
        return mapper.map(entidad, PagosPromocionResponseDTO.class);
    }
}
