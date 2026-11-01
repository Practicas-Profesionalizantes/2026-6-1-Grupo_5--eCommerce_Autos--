package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.TipoCombustibleRequestDTO;
import com.back.carfind.dtos.TipoCombustibleResponseDTO;
import com.back.carfind.model.TipoCombustible;
import com.back.carfind.service.TipoCombustibleService;

@RestController
@RequestMapping("/api/tipos-combustible")
@CrossOrigin(origins = "*")
public class TipoCombustibleController {

    private final TipoCombustibleService service;
    private final ModelMapper mapper;

    public TipoCombustibleController(TipoCombustibleService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TipoCombustibleResponseDTO crearTipoCombustible(@RequestBody TipoCombustibleRequestDTO dto) {
        return aDto(service.crearTipoCombustible(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public TipoCombustibleResponseDTO obtenerTipoCombustible(@PathVariable Long id) {
        return aDto(service.obtenerTipoCombustible(id));
    }

    @GetMapping
    public List<TipoCombustibleResponseDTO> listarTiposCombustible() {
        return service.listarTiposCombustible().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public TipoCombustibleResponseDTO actualizarTipoCombustible(@PathVariable Long id, @RequestBody TipoCombustibleRequestDTO dto) {
        return aDto(service.actualizarTipoCombustible(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoCombustible(@PathVariable Long id) {
        service.eliminarTipoCombustible(id);
    }

    private TipoCombustible aEntidad(TipoCombustibleRequestDTO dto) {
        return mapper.map(dto, TipoCombustible.class);
    }

    private TipoCombustibleResponseDTO aDto(TipoCombustible entidad) {
        return mapper.map(entidad, TipoCombustibleResponseDTO.class);
    }
}
