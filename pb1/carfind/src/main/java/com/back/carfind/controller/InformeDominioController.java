package com.back.carfind.controller;

import com.back.carfind.model.Informe_dominio;
import com.back.carfind.service.Informe_dominioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/informes-dominio")
@CrossOrigin(origins = "*")
public class InformeDominioController {

    private final Informe_dominioService service;

    public InformeDominioController(Informe_dominioService service) {
        this.service = service;
    }

    @PostMapping
    public Informe_dominio crearInformeDominio(@RequestBody Informe_dominio informeDominio) {
        return service.crearInforme(informeDominio);
    }

    @GetMapping("/{id}")
    public Informe_dominio obtenerInformeDominio(@PathVariable Long id) {
        return service.obtenerInforme(id);
    }

    @GetMapping
    public List<Informe_dominio> listarInformesDominio() {
        return service.listarInformes();
    }

    @PutMapping("/{id}")
    public Informe_dominio actualizarInformeDominio(@PathVariable Long id, @RequestBody Informe_dominio informeDominio) {
        return service.actualizarInforme(id, informeDominio);
    }

    @DeleteMapping("/{id}")
    public void eliminarInformeDominio(@PathVariable Long id) {
        service.eliminarInforme(id);
    }
}
