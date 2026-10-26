package com.back.carfind.dtos;

import java.time.LocalDate;

public class VistasPublicacionRequestDTO {

    private Long id_publicacion;
    private Long id_usuario;
    private LocalDate fecha_vista;

    // Getters y Setters
    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        id_publicacion = id_publicacion;
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
