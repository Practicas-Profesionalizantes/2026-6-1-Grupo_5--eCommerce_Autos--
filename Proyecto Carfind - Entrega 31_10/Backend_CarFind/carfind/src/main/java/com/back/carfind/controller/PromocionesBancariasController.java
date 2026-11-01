package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.PromocionesBancariasRequestDTO;
import com.back.carfind.dtos.PromocionesBancariasResponseDTO;
import com.back.carfind.model.PromocionesBancarias;
import com.back.carfind.service.PromocionesBancariasService;

@RestController
@RequestMapping("/api/promociones-bancarias")
@CrossOrigin(origins = "*")
public class PromocionesBancariasController {

    private final PromocionesBancariasService service;
    private final ModelMapper mapper;

    public PromocionesBancariasController(PromocionesBancariasService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public PromocionesBancariasResponseDTO crearPromocionBancaria(@RequestBody PromocionesBancariasRequestDTO dto) {
        return aDto(service.crearPromocionBancaria(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public PromocionesBancariasResponseDTO obtenerPromocionBancaria(@PathVariable Long id) {
        return aDto(service.obtenerPromocionBancaria(id));
    }

    @GetMapping
    public List<PromocionesBancariasResponseDTO> listarPromocionesBancarias() {
        return service.listarPromocionesBancarias().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public PromocionesBancariasResponseDTO actualizarPromocionBancaria(@PathVariable Long id, @RequestBody PromocionesBancariasRequestDTO dto) {
        return aDto(service.actualizarPromocionBancaria(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocionBancaria(@PathVariable Long id) {
        service.eliminarPromocionBancaria(id);
    }

    private PromocionesBancarias aEntidad(PromocionesBancariasRequestDTO dto) {
        return mapper.map(dto, PromocionesBancarias.class);
    }

    private PromocionesBancariasResponseDTO aDto(PromocionesBancarias entidad) {
        return mapper.map(entidad, PromocionesBancariasResponseDTO.class);
    }
}
