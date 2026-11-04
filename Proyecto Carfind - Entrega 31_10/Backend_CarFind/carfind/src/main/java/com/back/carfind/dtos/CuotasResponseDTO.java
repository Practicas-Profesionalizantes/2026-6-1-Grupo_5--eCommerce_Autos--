package com.back.carfind.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos que devuelve la API de un registro de la tabla cuotas.
 */
public class CuotasResponseDTO {

    private Long id;
    private Long pagoCuotaId;
    private Integer numeroCuota;
    private BigDecimal monto;
    private String estado;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;

    public CuotasResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPagoCuotaId() {
        return pagoCuotaId;
    }

    public void setPagoCuotaId(Long pagoCuotaId) {
        this.pagoCuotaId = pagoCuotaId;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
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

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
}
