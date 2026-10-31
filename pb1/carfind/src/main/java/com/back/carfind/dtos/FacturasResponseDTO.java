package com.back.carfind.dtos;

import java.time.LocalDate;

public class FacturasResponseDTO {

    private Long id_factura;
    private Long id_compra;
    private String numero_factura;
    private Float monto_total;
    private String cae;
    private LocalDate fecha_emision;

    // Constructor vacío
    public FacturasResponseDTO() {
    }

    // Constructor con parámetros
    public FacturasResponseDTO(Long id_factura, Long id_compra, String numero_factura, 
                               Float monto_total, String cae, LocalDate fecha_emision) {
        this.id_factura = id_factura;
        this.id_compra = id_compra;
        this.numero_factura = numero_factura;
        this.monto_total = monto_total;
        this.cae = cae;
        this.fecha_emision = fecha_emision;
    }

    // Getters y Setters
    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Long getId_compra() {
        return id_compra;
    }

    public void setId_compra(Long id_compra) {
        this.id_compra = id_compra;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public Float getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Float monto_total) {
        this.monto_total = monto_total;
    }

    public String getCae() {
        return cae;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }
}