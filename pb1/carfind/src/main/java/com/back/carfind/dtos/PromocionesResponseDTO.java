package com.back.carfind.dtos;

import java.time.LocalDate;

public class PromocionesResponseDTO {

    private Long id_promocion;
    private String nombre;
    private String descripcion;
    private Float descuento;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Boolean activo;

    // Constructor vacío
    public PromocionesResponseDTO() {
    }

    // Constructor con parámetros
    public PromocionesResponseDTO(Long id_promocion, String nombre, String descripcion, 
                                  Float descuento, LocalDate fecha_inicio, LocalDate fecha_fin, Boolean activo) {
        this.id_promocion = id_promocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(Long id_promocion) {
        this.id_promocion = id_promocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}