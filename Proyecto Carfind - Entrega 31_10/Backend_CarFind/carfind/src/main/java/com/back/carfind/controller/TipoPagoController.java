package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.TipoPagoRequestDTO;
import com.back.carfind.dtos.TipoPagoResponseDTO;
import com.back.carfind.model.TipoPago;
import com.back.carfind.service.TipoPagoService;

@RestController
@RequestMapping("/api/tipos-pago")
@CrossOrigin(origins = "*")
public class TipoPagoController {

    private final TipoPagoService service;
    private final ModelMapper mapper;

    public TipoPagoController(TipoPagoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TipoPagoResponseDTO crearTipoPago(@RequestBody TipoPagoRequestDTO dto) {
        return aDto(service.crearTipoPago(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public TipoPagoResponseDTO obtenerTipoPago(@PathVariable Long id) {
        return aDto(service.obtenerTipoPago(id));
    }

    @GetMapping
    public List<TipoPagoResponseDTO> listarTiposPago() {
        return service.listarTiposPago().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public TipoPagoResponseDTO actualizarTipoPago(@PathVariable Long id, @RequestBody TipoPagoRequestDTO dto) {
        return aDto(service.actualizarTipoPago(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoPago(@PathVariable Long id) {
        service.eliminarTipoPago(id);
    }

    private TipoPago aEntidad(TipoPagoRequestDTO dto) {
        return mapper.map(dto, TipoPago.class);
    }

    private TipoPagoResponseDTO aDto(TipoPago entidad) {
        return mapper.map(entidad, TipoPagoResponseDTO.class);
    }
}
