package com.back.carfind.dtos;

import java.time.LocalDate;

public class ReseniasResponseDTO {

    private Long id_resenia;
    private Long id_usuario;
    private Long id_publicacion;
    private Integer calificacion;
    private String comentario;
    private LocalDate fecha_resenia;

    // Constructor vacío
    public ReseniasResponseDTO() {
    }

    // Constructor con parámetros
    public ReseniasResponseDTO(Long id_resenia, Long id_usuario, Long id_publicacion, 
                               Integer calificacion, String comentario, LocalDate fecha_resenia) {
        this.id_resenia = id_resenia;
        this.id_usuario = id_usuario;
        this.id_publicacion = id_publicacion;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha_resenia = fecha_resenia;
    }

    // Getters y Setters
    public Long getId_resenia() {
        return id_resenia;
    }

    public void setId_resenia(Long id_resenia) {
        this.id_resenia = id_resenia;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha_resenia() {
        return fecha_resenia;
    }

    public void setFecha_resenia(LocalDate fecha_resenia) {
        this.fecha_resenia = fecha_resenia;
    }
}
