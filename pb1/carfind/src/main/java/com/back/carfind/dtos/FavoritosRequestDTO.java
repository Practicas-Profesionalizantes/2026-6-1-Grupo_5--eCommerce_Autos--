package com.back.carfind.dtos;

import java.time.LocalDate;

public class FavoritosRequestDTO {

    private Long id_usuario;
    private Long id_publicacion;
    private LocalDate fecha_agregado;

    // Getters y Setters
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

    public LocalDate getFecha_agregado() {
        return fecha_agregado;
    }

    public void setFecha_agregado(LocalDate fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }
}