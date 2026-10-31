package com.back.carfind.dtos;

import java.time.LocalDate;

public class MultimediaRequestDTO {

    private Long id_auto;
    private String url;
    private String tipo;
    private LocalDate fecha_subida;

    // Getters y Setters
    public Long getId_auto() {
        return id_auto;
    }

    public void setId_auto(Long id_auto) {
        this.id_auto = id_auto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(LocalDate fecha_subida) {
        this.fecha_subida = fecha_subida;
    }
}