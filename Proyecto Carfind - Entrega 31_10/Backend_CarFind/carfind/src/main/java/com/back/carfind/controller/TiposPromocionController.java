package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.TiposPromocionRequestDTO;
import com.back.carfind.dtos.TiposPromocionResponseDTO;
import com.back.carfind.model.TiposPromocion;
import com.back.carfind.service.TiposPromocionService;

@RestController
@RequestMapping("/api/tipos-promocion")
@CrossOrigin(origins = "*")
public class TiposPromocionController {

    private final TiposPromocionService service;
    private final ModelMapper mapper;

    public TiposPromocionController(TiposPromocionService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TiposPromocionResponseDTO crearTipoPromocion(@RequestBody TiposPromocionRequestDTO dto) {
        return aDto(service.crearTipoPromocion(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public TiposPromocionResponseDTO obtenerTipoPromocion(@PathVariable Long id) {
        return aDto(service.obtenerTipoPromocion(id));
    }

    @GetMapping
    public List<TiposPromocionResponseDTO> listarTiposPromocion() {
        return service.listarTiposPromocion().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public TiposPromocionResponseDTO actualizarTipoPromocion(@PathVariable Long id, @RequestBody TiposPromocionRequestDTO dto) {
        return aDto(service.actualizarTipoPromocion(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoPromocion(@PathVariable Long id) {
        service.eliminarTipoPromocion(id);
    }

    private TiposPromocion aEntidad(TiposPromocionRequestDTO dto) {
        return mapper.map(dto, TiposPromocion.class);
    }

    private TiposPromocionResponseDTO aDto(TiposPromocion entidad) {
        return mapper.map(entidad, TiposPromocionResponseDTO.class);
    }
}
