package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla busquedas_guardadas.
 */
public class BusquedasGuardadasResponseDTO {

    private Long id;
    private Long usuarioId;
    private String query;
    private LocalDateTime fecha;

    public BusquedasGuardadasResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
