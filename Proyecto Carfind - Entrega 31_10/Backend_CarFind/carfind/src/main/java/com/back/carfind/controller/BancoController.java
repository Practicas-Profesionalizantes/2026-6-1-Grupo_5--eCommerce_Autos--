package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.BancoRequestDTO;
import com.back.carfind.dtos.BancoResponseDTO;
import com.back.carfind.model.Banco;
import com.back.carfind.service.BancoService;

@RestController
@RequestMapping("/api/bancos")
@CrossOrigin(origins = "*")
public class BancoController {

    private final BancoService service;
    private final ModelMapper mapper;

    public BancoController(BancoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public BancoResponseDTO crearBanco(@RequestBody BancoRequestDTO dto) {
        return aDto(service.crearBanco(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public BancoResponseDTO obtenerBanco(@PathVariable Long id) {
        return aDto(service.obtenerBanco(id));
    }

    @GetMapping
    public List<BancoResponseDTO> listarBancos() {
        return service.listarBancos().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public BancoResponseDTO actualizarBanco(@PathVariable Long id, @RequestBody BancoRequestDTO dto) {
        return aDto(service.actualizarBanco(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarBanco(@PathVariable Long id) {
        service.eliminarBanco(id);
    }

    private Banco aEntidad(BancoRequestDTO dto) {
        return mapper.map(dto, Banco.class);
    }

    private BancoResponseDTO aDto(Banco entidad) {
        return mapper.map(entidad, BancoResponseDTO.class);
    }
}
