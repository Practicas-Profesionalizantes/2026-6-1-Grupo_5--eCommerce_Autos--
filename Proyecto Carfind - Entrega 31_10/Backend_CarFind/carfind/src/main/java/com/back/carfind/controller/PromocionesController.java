package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PromocionesRequestDTO;
import com.back.carfind.dtos.PromocionesResponseDTO;
import com.back.carfind.model.Promociones;
import com.back.carfind.service.PromocionesService;

@RestController
@RequestMapping("/api/promociones")
@CrossOrigin(origins = "*")
public class PromocionesController {

    private final PromocionesService service;
    private final ModelMapper mapper;

    public PromocionesController(PromocionesService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PromocionesResponseDTO crearPromocion(@RequestBody PromocionesRequestDTO dto) {
        return aDto(service.crearPromocion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PromocionesResponseDTO obtenerPromocion(@PathVariable Long id) {
        return aDto(service.obtenerPromocion(id));
    }

    @GetMapping
    public List<PromocionesResponseDTO> listarPromociones() {
        return service.listarPromociones().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PromocionesResponseDTO actualizarPromocion(@PathVariable Long id, @RequestBody PromocionesRequestDTO dto) {
        return aDto(service.actualizarPromocion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocion(@PathVariable Long id) {
        service.eliminarPromocion(id);
    }

    private Promociones aEntidad(PromocionesRequestDTO dto) {
        return mapper.map(dto, Promociones.class);
    }

    private PromocionesResponseDTO aDto(Promociones entidad) {
        return mapper.map(entidad, PromocionesResponseDTO.class);
    }
}
