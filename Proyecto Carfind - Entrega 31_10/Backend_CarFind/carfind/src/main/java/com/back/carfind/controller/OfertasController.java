package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.OfertasRequestDTO;
import com.back.carfind.dtos.OfertasResponseDTO;
import com.back.carfind.model.Ofertas;
import com.back.carfind.service.OfertasService;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertasController {

    private final OfertasService service;
    private final ModelMapper mapper;

    public OfertasController(OfertasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public OfertasResponseDTO crearOferta(@RequestBody OfertasRequestDTO dto) {
        return aDto(service.crearOferta(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public OfertasResponseDTO obtenerOferta(@PathVariable Long id) {
        return aDto(service.obtenerOferta(id));
    }

    @GetMapping
    public List<OfertasResponseDTO> listarOfertas() {
        return service.listarOfertas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public OfertasResponseDTO actualizarOferta(@PathVariable Long id, @RequestBody OfertasRequestDTO dto) {
        return aDto(service.actualizarOferta(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarOferta(@PathVariable Long id) {
        service.eliminarOferta(id);
    }

    private Ofertas aEntidad(OfertasRequestDTO dto) {
        return mapper.map(dto, Ofertas.class);
    }

    private OfertasResponseDTO aDto(Ofertas entidad) {
        return mapper.map(entidad, OfertasResponseDTO.class);
    }
}
