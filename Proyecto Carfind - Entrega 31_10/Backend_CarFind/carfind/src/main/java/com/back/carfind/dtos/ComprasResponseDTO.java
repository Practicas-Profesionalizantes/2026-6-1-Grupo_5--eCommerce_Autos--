package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla compras.
 */
public class ComprasResponseDTO {

    private Long id;
    private Long compradorId;
    private Long publicacionId;
    private BigDecimal precioFinal;
    private String estado;
    private LocalDateTime fecha;

    public ComprasResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Long compradorId) {
        this.compradorId = compradorId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
