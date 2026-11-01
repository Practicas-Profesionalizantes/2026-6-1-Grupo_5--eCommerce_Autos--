package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla historial_precios (sin el id autogenerado).
 */
public class HistorialPreciosRequestDTO {

    private Long publicacionId;
    private BigDecimal precio;
    private LocalDateTime fecha;

    public HistorialPreciosRequestDTO() {
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
