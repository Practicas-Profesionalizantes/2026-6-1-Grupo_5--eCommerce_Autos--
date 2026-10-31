package com.back.carfind.dtos;

import java.time.LocalDate;

public class ActividadSospechosaRequestDTO {

    private Long id_usuario;
    private String tipo_actividad_sospechosa;
    private String descripcion_actividad_sospechosa;
    private LocalDate fecha_actividad_sospechosa;

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_actividad_sospechosa() {
        return tipo_actividad_sospechosa;
    }

    public void setTipo_actividad_sospechosa(String tipo_actividad_sospechosa) {
        this.tipo_actividad_sospechosa = tipo_actividad_sospechosa;
    }

    public String getDescripcion_actividad_sospechosa() {
        return descripcion_actividad_sospechosa;
    }

    public void setDescripcion_actividad_sospechosa(String descripcion_actividad_sospechosa) {
        this.descripcion_actividad_sospechosa = descripcion_actividad_sospechosa;
    }

    public LocalDate getFecha_actividad_sospechosa() {
        return fecha_actividad_sospechosa;
    }

    public void setFecha_actividad_sospechosa(LocalDate fecha_actividad_sospechosa) {
        this.fecha_actividad_sospechosa = fecha_actividad_sospechosa;
    }
}