package com.back.carfind.dtos;

import java.time.LocalDate;

public class ComprasResponseDTO {

    private Long id_compra;
    private Long id_comprador;
    private Long id_publicacion;
    private Float precio_final;
    private String estado;
    private LocalDate fecha_compra;

    // Constructor vacío
    public ComprasResponseDTO() {
    }

    // Constructor con parámetros
    public ComprasResponseDTO(Long id_compra, Long id_comprador, Long id_publicacion, 
                              Float precio_final, String estado, LocalDate fecha_compra) {
        this.id_compra = id_compra;
        this.id_comprador = id_comprador;
        this.id_publicacion = id_publicacion;
        this.precio_final = precio_final;
        this.estado = estado;
        this.fecha_compra = fecha_compra;
    }

    // Getters y Setters
    public Long getId_compra() {
        return id_compra;
    }

    public void setId_compra(Long id_compra) {
        this.id_compra = id_compra;
    }

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