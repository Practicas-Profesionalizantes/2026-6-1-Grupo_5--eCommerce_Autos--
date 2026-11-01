package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla actividad_usuario.
 */
public class ActividadUsuarioResponseDTO {

    private Long id;
    private Long usuarioId;
    private String accion;
    private String descripcion;
    private LocalDateTime fecha;

    public ActividadUsuarioResponseDTO() {
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
