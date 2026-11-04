package com.back.carfind.web;

import java.time.Duration;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sirve las imágenes subidas: GET /uploads/<nombre>.
 * El nombre es aleatorio y nunca se reutiliza, así que el navegador puede guardarlas en caché.
 */
@RestController
public class ArchivosController {

    private final ArchivoService archivos;

    public ArchivosController(ArchivoService archivos) {
        this.archivos = archivos;
    }

    @GetMapping("/uploads/{nombre}")
    public ResponseEntity<byte[]> obtener(@PathVariable String nombre) {
        return archivos.buscar(nombre)
                .map(a -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(a.tipo()))
                        .cacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic().immutable())
                        .body(a.datos()))
                .orElse(ResponseEntity.notFound().build());
    }
}
