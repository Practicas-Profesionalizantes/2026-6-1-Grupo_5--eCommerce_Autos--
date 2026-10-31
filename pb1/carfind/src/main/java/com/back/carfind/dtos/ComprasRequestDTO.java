package com.back.carfind.dtos;

import java.time.LocalDate;

public class ComprasRequestDTO {

    private Long id_comprador;
    private Long id_publicacion;
    private Float precio_final;
    private String estado;
    private LocalDate fecha_compra;

    // Getters y Setters
    public Long getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(Long id_comprador) {
        this.id_comprador = id_comprador;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Float getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(Float precio_final) {
        this.precio_final = precio_final;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }
}