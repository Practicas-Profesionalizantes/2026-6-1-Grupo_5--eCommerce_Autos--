package com.back.carfind.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import com.back.carfind.dtos.InformeDominioRequestDTO;
import com.back.carfind.dtos.InformeDominioResponseDTO;
import com.back.carfind.model.InformeDominio;
import com.back.carfind.service.InformeDominioService;

@RestController
@RequestMapping("/api/informes-dominio")
@CrossOrigin(origins = "*")
public class InformeDominioController {

    private final InformeDominioService service;
    private final ModelMapper mapper;

    public InformeDominioController(InformeDominioService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public InformeDominioResponseDTO crearInformeDominio(@RequestBody InformeDominioRequestDTO dto) {
        return aDto(service.crearInformeDominio(aEntidad(dto)));
    }

    @GetMapping("/{id}")
    public InformeDominioResponseDTO obtenerInformeDominio(@PathVariable Long id) {
        return aDto(service.obtenerInformeDominio(id));
    }

    @GetMapping
    public List<InformeDominioResponseDTO> listarInformesDominio() {
        return service.listarInformesDominio().stream().map(this::aDto).toList();
    }

    @PutMapping("/{id}")
    public InformeDominioResponseDTO actualizarInformeDominio(@PathVariable Long id, @RequestBody InformeDominioRequestDTO dto) {
        return aDto(service.actualizarInformeDominio(id, aEntidad(dto)));
    }

    @DeleteMapping("/{id}")
    public void eliminarInformeDominio(@PathVariable Long id) {
        service.eliminarInformeDominio(id);
    }

    private InformeDominio aEntidad(InformeDominioRequestDTO dto) {
        return mapper.map(dto, InformeDominio.class);
    }

    private InformeDominioResponseDTO aDto(InformeDominio entidad) {
        return mapper.map(entidad, InformeDominioResponseDTO.class);
    }
}
