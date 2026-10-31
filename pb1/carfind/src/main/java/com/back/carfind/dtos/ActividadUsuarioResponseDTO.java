package com.back.carfind.dtos;

import java.time.LocalDate;

public class ActividadUsuarioResponseDTO {

    private Long id_actividadUsuario;
    private String accion;
    private String descripcion;
    private LocalDate fecha_accion;

    // Constructor vacío
    public ActividadUsuarioResponseDTO() {
    }

    // Constructor con parámetros
    public ActividadUsuarioResponseDTO(Long id_actividadUsuario, String accion, String descripcion, LocalDate fecha_accion) {
        this.id_actividadUsuario = id_actividadUsuario;
        this.accion = accion;
        this.descripcion = descripcion;
        this.fecha_accion = fecha_accion;
    }

    // Getters y Setters
    public Long getId_actividadUsuario() {
        return id_actividadUsuario;
    }

    public void setId_actividadUsuario(Long id_actividadUsuario) {
        this.id_actividadUsuario = id_actividadUsuario;
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

    public LocalDate getFecha_accion() {
        return fecha_accion;
    }

    public void setFecha_accion(LocalDate fecha_accion) {
        this.fecha_accion = fecha_accion;
    }
}