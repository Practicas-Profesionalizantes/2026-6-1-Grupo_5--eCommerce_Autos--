package com.back.carfind.dtos;

import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla transacciones (sin el id autogenerado).
 */
public class TransaccionesRequestDTO {

    private Long compraId;
    private Long tipoPagoId;
    private String estado;
    private String idExterno;
    private LocalDateTime fecha;

    public TransaccionesRequestDTO() {
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public Long getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
