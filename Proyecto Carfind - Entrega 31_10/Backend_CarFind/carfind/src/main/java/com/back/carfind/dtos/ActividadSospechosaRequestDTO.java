package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla actividad_sospechosa (sin el id autogenerado).
 */
public class ActividadSospechosaRequestDTO {

    private Long usuarioId;
    private String tipo;
    private String descripcion;
    private LocalDateTime fecha;

    public ActividadSospechosaRequestDTO() {
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
