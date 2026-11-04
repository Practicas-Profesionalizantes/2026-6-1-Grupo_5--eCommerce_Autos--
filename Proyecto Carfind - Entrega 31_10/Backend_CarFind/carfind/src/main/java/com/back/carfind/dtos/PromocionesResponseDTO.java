package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla promociones.
 */
public class PromocionesResponseDTO {

    private Long id;
    private Long publicacionId;
    private Long tipoPromocionId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;

    public PromocionesResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
