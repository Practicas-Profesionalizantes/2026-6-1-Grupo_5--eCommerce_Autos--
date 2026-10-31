package com.back.carfind.dtos;

import java.time.LocalDate;

public class CuotasResponseDTO {

    private Long id_compra;
    private Integer numero_cuota;
    private Float monto;
    private LocalDate fecha_vencimiento;
    private String estado;

    // Getters y Setters
    public Long getId_compra() {
        return id_compra;
    }

    public void setId_compra(Long id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getNumero_cuota() {
        return numero_cuota;
    }

    public void setNumero_cuota(Integer numero_cuota) {
        this.numero_cuota = numero_cuota;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}