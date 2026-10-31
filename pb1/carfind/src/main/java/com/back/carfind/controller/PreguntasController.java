package com.back.carfind.controller;

import com.back.carfind.model.Preguntas;
import com.back.carfind.service.PreguntasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin(origins = "*")
public class PreguntasController {

    private final PreguntasService service;

    public PreguntasController(PreguntasService service) {
        this.service = service;
    }

    @PostMapping
    public Preguntas crearPregunta(@RequestBody Preguntas pregunta) {
        return service.crearPregunta(pregunta);
    }

    @GetMapping("/{id}")
    public Preguntas obtenerPregunta(@PathVariable Long id) {
        return service.obtenerPregunta(id);
    }

    @GetMapping
    public List<Preguntas> listarPreguntas() {
        return service.listarPreguntas();
    }

    @PutMapping("/{id}")
    public Preguntas actualizarPregunta(@PathVariable Long id, @RequestBody Preguntas pregunta) {
        return service.actualizarPregunta(id, pregunta);
    }

    @DeleteMapping("/{id}")
    public void eliminarPregunta(@PathVariable Long id) {
        service.eliminarPregunta(id);
    }
}
