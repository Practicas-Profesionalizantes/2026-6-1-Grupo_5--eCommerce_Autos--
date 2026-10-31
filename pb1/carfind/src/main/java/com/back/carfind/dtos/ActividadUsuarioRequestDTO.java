package com.back.carfind.dtos;

import java.time.LocalDate;

public class ActividadUsuarioRequestDTO {

    private String accion;
    private String descripcion;
    private LocalDate fecha_accion;

    // Getters y Setters
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

    public LocalDate getFecha_accion() {
        return fecha_accion;
    }

    public void setFecha_accion(LocalDate fecha_accion) {
        this.fecha_accion = fecha_accion;
    }
}