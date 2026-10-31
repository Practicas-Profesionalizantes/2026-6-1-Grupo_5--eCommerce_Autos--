package com.back.carfind.dtos;

import java.time.LocalDate;

public class PublicacionesRequestDTO {

    private Long id_pregunta;
    private Long id_publicacion;
    private Long id_usuario;
    private String pregunta;
    private String respuesta;
    private LocalDate fecha_pregunta;
    private LocalDate fecha_respuesta;

    // Constructor vacío
    public PublicacionesRequestDTO() {
    }

    // Constructor con parámetros
    public PublicacionesRequestDTO(Long id_pregunta, Long id_publicacion, Long id_usuario, 
                                 String pregunta, String respuesta, LocalDate fecha_pregunta, LocalDate fecha_respuesta) {
        this.id_pregunta = id_pregunta;
        this.id_publicacion = id_publicacion;
        this.id_usuario = id_usuario;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.fecha_pregunta = fecha_pregunta;
        this.fecha_respuesta = fecha_respuesta;
    }

    // Getters y Setters
    public Long getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(Long id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public LocalDate getFecha_pregunta() {
        return fecha_pregunta;
    }

    public void setFecha_pregunta(LocalDate fecha_pregunta) {
        this.fecha_pregunta = fecha_pregunta;
    }

    public LocalDate getFecha_respuesta() {
        return fecha_respuesta;
    }

    public void setFecha_respuesta(LocalDate fecha_respuesta) {
        this.fecha_respuesta = fecha_respuesta;
    }
}