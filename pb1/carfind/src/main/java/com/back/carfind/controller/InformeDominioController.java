package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.InformeDominio;
import com.back.carfind.service.InformeDominioService;

@RestController
@RequestMapping("/api/informes-dominio")
@CrossOrigin(origins = "*")
public class InformeDominioController {

    private final InformeDominioService service;

    public InformeDominioController(InformeDominioService service) {
        this.service = service;
    }

    @PostMapping
    public InformeDominio crearInformeDominio(@RequestBody InformeDominio informeDominio) {
        return service.crearInformeDominio(informeDominio);
    }

    @GetMapping("/{id}")
    public InformeDominio obtenerInformeDominio(@PathVariable Long id) {
        return service.obtenerInformeDominio(id);
    }

    @GetMapping
    public List<InformeDominio> listarInformesDominio() {
        return service.listarInformesDominio();
    }

    @PutMapping("/{id}")
    public InformeDominio actualizarInformeDominio(@PathVariable Long id, @RequestBody InformeDominio informeDominio) {
        return service.actualizarInformeDominio(id, informeDominio);
    }

    @DeleteMapping("/{id}")
    public void eliminarInformeDominio(@PathVariable Long id) {
        service.eliminarInformeDominio(id);
    }
}
