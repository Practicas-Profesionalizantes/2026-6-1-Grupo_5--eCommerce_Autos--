package com.back.carfind.dtos;

import java.time.LocalDate;

public class ModeracionMultimediaResponseDTO {

    private Long id_moderacion;
    private Long id_multimedia;
    private Long id_moderador;
    private String estado;
    private String motivo;
    private LocalDate fecha_moderacion;

    // Constructor vacío
    public ModeracionMultimediaResponseDTO() {
    }

    // Constructor con parámetros
    public ModeracionMultimediaResponseDTO(Long id_moderacion, Long id_multimedia, Long id_moderador, 
                                          String estado, String motivo, LocalDate fecha_moderacion) {
        this.id_moderacion = id_moderacion;
        this.id_multimedia = id_multimedia;
        this.id_moderador = id_moderador;
        this.estado = estado;
        this.motivo = motivo;
        this.fecha_moderacion = fecha_moderacion;
    }

    // Getters y Setters
    public Long getId_moderacion() {
        return id_moderacion;
    }

    public void setId_moderacion(Long id_moderacion) {
        this.id_moderacion = id_moderacion;
    }

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