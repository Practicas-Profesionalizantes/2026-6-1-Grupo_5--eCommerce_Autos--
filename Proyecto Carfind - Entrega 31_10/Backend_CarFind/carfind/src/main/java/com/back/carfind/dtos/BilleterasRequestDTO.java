package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla billeteras (sin el id autogenerado).
 */
public class BilleterasRequestDTO {

    private Long usuarioId;
    private BigDecimal saldo;

    public BilleterasRequestDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
