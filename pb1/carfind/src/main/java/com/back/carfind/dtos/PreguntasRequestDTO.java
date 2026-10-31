package com.back.carfind.dtos;

import java.time.LocalDate;

public class PreguntasRequestDTO {

    private Long id_publicacion;
    private Long id_usuario;
    private String pregunta;
    private LocalDate fecha_pregunta;

    // Getters y Setters
    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public LocalDate getFecha_pregunta() {
        return fecha_pregunta;
    }

    public void setFecha_pregunta(LocalDate fecha_pregunta) {
        this.fecha_pregunta = fecha_pregunta;
    }
}