package com.back.carfind.dtos;

import java.time.LocalDate;

public class OfertasResponseDTO {

    private Long id_oferta;
    private Long id_publicacion;
    private Long id_comprador;
    private Float monto_ofertado;
    private String estado;
    private LocalDate fecha_oferta;

    // Constructor vacío
    public OfertasResponseDTO() {
    }

    // Constructor con parámetros
    public OfertasResponseDTO(Long id_oferta, Long id_publicacion, Long id_comprador, 
                              Float monto_ofertado, String estado, LocalDate fecha_oferta) {
        this.id_oferta = id_oferta;
        this.id_publicacion = id_publicacion;
        this.id_comprador = id_comprador;
        this.monto_ofertado = monto_ofertado;
        this.estado = estado;
        this.fecha_oferta = fecha_oferta;
    }

    // Getters y Setters
    public Long getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(Long id_oferta) {
        this.id_oferta = id_oferta;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Long getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(Long id_comprador) {
        this.id_comprador = id_comprador;
    }

    public Float getMonto_ofertado() {
        return monto_ofertado;
    }

    public void setMonto_ofertado(Float monto_ofertado) {
        this.monto_ofertado = monto_ofertado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_oferta() {
        return fecha_oferta;
    }

    public void setFecha_oferta(LocalDate fecha_oferta) {
        this.fecha_oferta = fecha_oferta;
    }
}