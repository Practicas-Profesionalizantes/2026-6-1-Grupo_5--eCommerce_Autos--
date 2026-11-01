package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PagosCuotasRequestDTO;
import com.back.carfind.dtos.PagosCuotasResponseDTO;
import com.back.carfind.model.PagosCuotas;
import com.back.carfind.service.PagosCuotasService;

@RestController
@RequestMapping("/api/pagos-cuotas")
@CrossOrigin(origins = "*")
public class PagosCuotasController {

    private final PagosCuotasService service;
    private final ModelMapper mapper;

    public PagosCuotasController(PagosCuotasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PagosCuotasResponseDTO crearPagoCuota(@RequestBody PagosCuotasRequestDTO dto) {
        return aDto(service.crearPagoCuota(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PagosCuotasResponseDTO obtenerPagoCuota(@PathVariable Long id) {
        return aDto(service.obtenerPagoCuota(id));
    }

    @GetMapping
    public List<PagosCuotasResponseDTO> listarPagosCuotas() {
        return service.listarPagosCuotas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PagosCuotasResponseDTO actualizarPagoCuota(@PathVariable Long id, @RequestBody PagosCuotasRequestDTO dto) {
        return aDto(service.actualizarPagoCuota(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPagoCuota(@PathVariable Long id) {
        service.eliminarPagoCuota(id);
    }

    private PagosCuotas aEntidad(PagosCuotasRequestDTO dto) {
        return mapper.map(dto, PagosCuotas.class);
    }

    private PagosCuotasResponseDTO aDto(PagosCuotas entidad) {
        return mapper.map(entidad, PagosCuotasResponseDTO.class);
    }
}
