package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que devuelve la API de un registro de la tabla comisiones.
 */
public class ComisionesResponseDTO {

    private Long id;
    private Long compraId;
    private BigDecimal porcentaje;
    private BigDecimal monto;

    public ComisionesResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
