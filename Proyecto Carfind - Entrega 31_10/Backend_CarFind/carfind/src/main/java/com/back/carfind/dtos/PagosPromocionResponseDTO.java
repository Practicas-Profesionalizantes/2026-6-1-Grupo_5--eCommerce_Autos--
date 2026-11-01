package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Datos que devuelve la API de un registro de la tabla pagos_promocion.
 */
public class PagosPromocionResponseDTO {

    private Long id;
    private Long promocionId;
    private BigDecimal monto;
    private String estado;
    private LocalDateTime fecha;

    public PagosPromocionResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
