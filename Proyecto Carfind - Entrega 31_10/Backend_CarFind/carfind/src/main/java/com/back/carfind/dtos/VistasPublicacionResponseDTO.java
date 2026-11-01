package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla vistas_publicacion.
 */
public class VistasPublicacionResponseDTO {

    private Long usuarioId;
    private Long publicacionId;
    private LocalDateTime fecha;

    public VistasPublicacionResponseDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
