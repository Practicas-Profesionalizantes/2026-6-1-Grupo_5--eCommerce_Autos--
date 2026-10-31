package com.back.carfind.dtos;

import java.time.LocalDate;

public class PagosResponseDTO {

    private Long id_pago;
    private Long id_compra;
    private Float monto;
    private String metodo_pago;
    private String estado;
    private LocalDate fecha_pago;

    // Constructor vacío
    public PagosResponseDTO() {
    }

    // Constructor con parámetros
    public PagosResponseDTO(Long id_pago, Long id_compra, Float monto, String metodo_pago, String estado, LocalDate fecha_pago) {
        this.id_pago = id_pago;
        this.id_compra = id_compra;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.estado = estado;
        this.fecha_pago = fecha_pago;
    }

    // Getters y Setters
    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public Long getId_compra() {
        return id_compra;
    }

    public void setId_compra(Long id_compra) {
        this.id_compra = id_compra;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
}