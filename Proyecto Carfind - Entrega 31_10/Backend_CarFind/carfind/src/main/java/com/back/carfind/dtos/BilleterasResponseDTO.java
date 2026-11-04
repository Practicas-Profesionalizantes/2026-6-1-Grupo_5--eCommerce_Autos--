package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que devuelve la API de un registro de la tabla billeteras.
 */
public class BilleterasResponseDTO {

    private Long usuarioId;
    private BigDecimal saldo;

    public BilleterasResponseDTO() {
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
