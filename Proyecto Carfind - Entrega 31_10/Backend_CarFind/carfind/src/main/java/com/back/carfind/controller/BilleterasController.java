package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.BilleterasRequestDTO;
import com.back.carfind.dtos.BilleterasResponseDTO;
import com.back.carfind.model.Billeteras;
import com.back.carfind.service.BilleterasService;

@RestController
@RequestMapping("/api/billeteras")
@CrossOrigin(origins = "*")
public class BilleterasController {

    private final BilleterasService service;
    private final ModelMapper mapper;

    public BilleterasController(BilleterasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public BilleterasResponseDTO crearBilletera(@RequestBody BilleterasRequestDTO dto) {
        return aDto(service.crearBilletera(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public BilleterasResponseDTO obtenerBilletera(@PathVariable Long id) {
        return aDto(service.obtenerBilletera(id));
    }

    @GetMapping
    public List<BilleterasResponseDTO> listarBilleteras() {
        return service.listarBilleteras().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public BilleterasResponseDTO actualizarBilletera(@PathVariable Long id, @RequestBody BilleterasRequestDTO dto) {
        return aDto(service.actualizarBilletera(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarBilletera(@PathVariable Long id) {
        service.eliminarBilletera(id);
    }

    private Billeteras aEntidad(BilleterasRequestDTO dto) {
        return mapper.map(dto, Billeteras.class);
    }

    private BilleterasResponseDTO aDto(Billeteras entidad) {
        return mapper.map(entidad, BilleterasResponseDTO.class);
    }
}
