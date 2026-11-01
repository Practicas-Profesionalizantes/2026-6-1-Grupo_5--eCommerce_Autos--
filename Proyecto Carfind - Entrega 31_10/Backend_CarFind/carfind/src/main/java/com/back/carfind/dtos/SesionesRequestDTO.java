package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla sesiones (sin el id autogenerado).
 */
public class SesionesRequestDTO {

    private Long usuarioId;
    private String token;
    private LocalDateTime fechaExpiracion;

    public SesionesRequestDTO() {
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
