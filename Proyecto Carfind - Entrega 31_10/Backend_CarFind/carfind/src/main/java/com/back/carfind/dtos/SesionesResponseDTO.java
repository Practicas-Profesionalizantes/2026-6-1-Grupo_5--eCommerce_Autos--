package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla sesiones.
 */
public class SesionesResponseDTO {

    private Long id;
    private Long usuarioId;
    private String token;
    private LocalDateTime fechaExpiracion;

    public SesionesResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
