package com.back.carfind.dtos;

import java.time.LocalDate;

public class VistasPublicacionResponseDTO {

    private Long id_vista;
    private Long id_publicacion;
    private Long id_usuario;
    private LocalDate fecha_vista;

    // Constructor vacío
    public VistasPublicacionResponseDTO() {
    }

    // Constructor con parámetros
    public VistasPublicacionResponseDTO(Long id_vista, Long id_publicacion, Long id_usuario, LocalDate fecha_vista) {
        this.id_vista = id_vista;
        this.id_publicacion = id_publicacion;
        this.id_usuario = id_usuario;
        this.fecha_vista = fecha_vista;
    }

    // Getters y Setters
    public Long getId_vista() {
        return id_vista;
    }

    public void setId_vista(Long id_vista) {
        this.id_vista = id_vista;
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

    public LocalDate getFecha_vista() {
        return fecha_vista;
    }

    public void setFecha_vista(LocalDate fecha_vista) {
        this.fecha_vista = fecha_vista;
    }
}
