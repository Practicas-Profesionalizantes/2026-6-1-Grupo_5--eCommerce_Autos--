package com.back.carfind.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

/**
 * El frontend muestra el campo "message" de las respuestas de error (ver src/services/api.js).
 */
@RestControllerAdvice(basePackages = "com.back.carfind.web")
public class ApiErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> manejar(ResponseStatusException e) {
        String mensaje = e.getReason() != null ? e.getReason() : "Ocurrió un error. Intentá de nuevo.";
        return ResponseEntity.status(e.getStatusCode()).body(Map.of("message", mensaje));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> archivoGrande(MaxUploadSizeExceededException e) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(Map.of("message", "La imagen es demasiado grande (máximo 10 MB)."));
    }
}
