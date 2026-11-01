package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla notificaciones (sin el id autogenerado).
 */
public class NotificacionesRequestDTO {

    private Long usuarioId;
    private String tipo;
    private String mensaje;
    private Boolean leido;
    private Long entidadId;
    private LocalDateTime fecha;

    public NotificacionesRequestDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Long getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Long entidadId) {
        this.entidadId = entidadId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
