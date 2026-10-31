package com.back.carfind.dtos;

import java.time.LocalDate;

public class ModeracionMultimediaRequestDTO {

    private Long id_multimedia;
    private Long id_moderador;
    private String estado;
    private String motivo;
    private LocalDate fecha_moderacion;

    // Getters y Setters
    public Long getId_multimedia() {
        return id_multimedia;
    }

    public void setId_multimedia(Long id_multimedia) {
        this.id_multimedia = id_multimedia;
    }

    public Long getId_moderador() {
        return id_moderador;
    }

    public void setId_moderador(Long id_moderador) {
        this.id_moderador = id_moderador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha_moderacion() {
        return fecha_moderacion;
    }

    public void setFecha_moderacion(LocalDate fecha_moderacion) {
        this.fecha_moderacion = fecha_moderacion;
    }
}