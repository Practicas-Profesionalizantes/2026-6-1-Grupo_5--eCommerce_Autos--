package com.back.carfind.dtos;

import java.time.LocalDate;

public class PagosCuotasRequestDTO {

    private Long id_pago;
    private Integer numero_cuota;
    private Float monto_cuota;
    private String estado;
    private LocalDate fecha_vencimiento;

    // Getters y Setters
    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public Integer getNumero_cuota() {
        return numero_cuota;
    }

    public void setNumero_cuota(Integer numero_cuota) {
        this.numero_cuota = numero_cuota;
    }

    public Float getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(Float monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}