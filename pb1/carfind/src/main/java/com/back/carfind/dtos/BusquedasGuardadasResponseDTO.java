package com.back.carfind.dtos;

import java.time.LocalDate;

public class BusquedasGuardadasResponseDTO {

    private Long usuario_id;
    private String query;
    private LocalDate fecha;

    // Constructor vacío
    public BusquedasGuardadasResponseDTO() {
    }

    // Constructor con parámetros
    public BusquedasGuardadasResponseDTO(Long usuario_id, String query, LocalDate fecha) {
        this.usuario_id = usuario_id;
        this.query = query;
        this.fecha = fecha;
    }

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