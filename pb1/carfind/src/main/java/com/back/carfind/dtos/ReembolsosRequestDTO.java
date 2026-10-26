package com.back.carfind.dtos;

import java.time.LocalDate;

public class ReembolsosRequestDTO {

    private Long id_pago;
    private Float monto;
    private String motivo;
    private String estado;
    private LocalDate fecha_reembolso;

    // Getters y Setters
    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_reembolso() {
        return fecha_reembolso;
    }

    public void setFecha_reembolso(LocalDate fecha_reembolso) {
        this.fecha_reembolso = fecha_reembolso;
    }
}
