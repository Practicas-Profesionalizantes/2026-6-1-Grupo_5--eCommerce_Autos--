package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla promociones (sin el id autogenerado).
 */
public class PromocionesRequestDTO {

    private Long publicacionId;
    private Long tipoPromocionId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;

    public PromocionesRequestDTO() {
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Long getTipoPromocionId() {
        return tipoPromocionId;
    }

    public void setTipoPromocionId(Long tipoPromocionId) {
        this.tipoPromocionId = tipoPromocionId;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
