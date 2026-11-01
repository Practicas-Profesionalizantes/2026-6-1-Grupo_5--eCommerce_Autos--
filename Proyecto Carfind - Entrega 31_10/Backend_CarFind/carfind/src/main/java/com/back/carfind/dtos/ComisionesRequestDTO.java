package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla comisiones (sin el id autogenerado).
 */
public class ComisionesRequestDTO {

    private Long compraId;
    private BigDecimal porcentaje;
    private BigDecimal monto;

    public ComisionesRequestDTO() {
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
