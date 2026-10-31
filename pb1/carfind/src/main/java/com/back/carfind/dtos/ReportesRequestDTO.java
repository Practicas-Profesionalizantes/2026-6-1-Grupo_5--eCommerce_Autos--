package com.back.carfind.dtos;

import java.time.LocalDate;

public class ReportesRequestDTO {

    private Long id_usuario;
    private String tipo_reporte;
    private String motivo;
    private String descripcion;
    private String estado;
    private LocalDate fecha_reporte;

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_reporte() {
        return tipo_reporte;
    }

    public void setTipo_reporte(String tipo_reporte) {
        this.tipo_reporte = tipo_reporte;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_reporte() {
        return fecha_reporte;
    }

    public void setFecha_reporte(LocalDate fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }
}