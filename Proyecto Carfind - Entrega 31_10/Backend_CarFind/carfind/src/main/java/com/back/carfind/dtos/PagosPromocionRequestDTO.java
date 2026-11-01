package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla pagos_promocion (sin el id autogenerado).
 */
public class PagosPromocionRequestDTO {

    private Long promocionId;
    private BigDecimal monto;
    private String estado;
    private LocalDateTime fecha;

    public PagosPromocionRequestDTO() {
    }

    public Long getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(Long promocionId) {
        this.promocionId = promocionId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
