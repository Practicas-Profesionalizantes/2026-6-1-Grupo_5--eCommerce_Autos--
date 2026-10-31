package com.back.carfind.dtos;

public class ComisionesResponseDTO {

    private Long id_compra;
    private Float porcentaje;
    private Float monto;

    // Constructor vacío
    public ComisionesResponseDTO() {
    }

    // Constructor con parámetros
    public ComisionesResponseDTO(Long id_compra, Float porcentaje, Float monto) {
        this.id_compra = id_compra;
        this.porcentaje = porcentaje;
        this.monto = monto;
    }

    // Getters y Setters
    public Long getId_compra() {
        return id_compra;
    }

    public void setId_compra(Long id_compra) {
        this.id_compra = id_compra;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }
}