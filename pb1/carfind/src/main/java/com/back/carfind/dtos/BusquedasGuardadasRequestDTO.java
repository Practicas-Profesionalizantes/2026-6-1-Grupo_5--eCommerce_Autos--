package com.back.carfind.dtos;

import java.time.LocalDate;

public class BusquedasGuardadasRequestDTO {

    private Long usuario_id;
    private String query;
    private LocalDate fecha;

    // Getters y Setters
    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}