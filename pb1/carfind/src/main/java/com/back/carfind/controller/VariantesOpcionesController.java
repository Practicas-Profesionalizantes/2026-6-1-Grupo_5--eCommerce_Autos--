package com.back.carfind.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.back.carfind.model.VariantesOpciones;
import com.back.carfind.service.VariantesOpcionesService;

@RestController
@RequestMapping("/api/variantes-opciones")
@CrossOrigin(origins = "*")
public class VariantesOpcionesController {

    private final VariantesOpcionesService service;

    public VariantesOpcionesController(VariantesOpcionesService service) {
        this.service = service;
    }

    @PostMapping
    public VariantesOpciones crearVarianteOpcion(@RequestBody VariantesOpciones varianteOpcion) {
        return service.crearVarianteOpcion(varianteOpcion);
    }

    @GetMapping("/{id}")
    public VariantesOpciones obtenerVarianteOpcion(@PathVariable Long id) {
        return service.obtenerVarianteOpcion(id);
    }

    @GetMapping
    public List<VariantesOpciones> listarVariantesOpciones() {
        return service.listarVariantesOpciones();
    }

    @PutMapping("/{id}")
    public VariantesOpciones actualizarVarianteOpcion(@PathVariable Long id, @RequestBody VariantesOpciones varianteOpcion) {
        return service.actualizarVarianteOpcion(id, varianteOpcion);
    }

    @DeleteMapping("/{id}")
    public void eliminarVarianteOpcion(@PathVariable Long id) {
        service.eliminarVarianteOpcion(id);
    }
}
