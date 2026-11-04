package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.BusquedasGuardadasRequestDTO;
import com.back.carfind.dtos.BusquedasGuardadasResponseDTO;
import com.back.carfind.model.BusquedasGuardadas;
import com.back.carfind.service.BusquedasGuardadasService;

@RestController
@RequestMapping("/api/busquedas-guardadas")
@CrossOrigin(origins = "*")
public class BusquedasGuardadasController {

    private final BusquedasGuardadasService service;
    private final ModelMapper mapper;

    public BusquedasGuardadasController(BusquedasGuardadasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public BusquedasGuardadasResponseDTO crearBusquedaGuardada(@RequestBody BusquedasGuardadasRequestDTO dto) {
        return aDto(service.crearBusquedaGuardada(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public BusquedasGuardadasResponseDTO obtenerBusquedaGuardada(@PathVariable Long id) {
        return aDto(service.obtenerBusquedaGuardada(id));
    }

    @GetMapping
    public List<BusquedasGuardadasResponseDTO> listarBusquedasGuardadas() {
        return service.listarBusquedasGuardadas().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public BusquedasGuardadasResponseDTO actualizarBusquedaGuardada(@PathVariable Long id, @RequestBody BusquedasGuardadasRequestDTO dto) {
        return aDto(service.actualizarBusquedaGuardada(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarBusquedaGuardada(@PathVariable Long id) {
        service.eliminarBusquedaGuardada(id);
    }

    private BusquedasGuardadas aEntidad(BusquedasGuardadasRequestDTO dto) {
        return mapper.map(dto, BusquedasGuardadas.class);
    }

    private BusquedasGuardadasResponseDTO aDto(BusquedasGuardadas entidad) {
        return mapper.map(entidad, BusquedasGuardadasResponseDTO.class);
    }
}
