package com.back.carfind.dtos;

import java.time.LocalDate;

public class InventarioRequestDTO {

    private Long id_auto;
    private Integer cantidad;
    private String ubicacion_almacen;
    private LocalDate fecha_ingreso;

    // Getters y Setters
    public Long getId_auto() {
        return id_auto;
    }

    public void setId_auto(Long id_auto) {
        this.id_auto = id_auto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion_almacen() {
        return ubicacion_almacen;
    }

    public void setUbicacion_almacen(String ubicacion_almacen) {
        this.ubicacion_almacen = ubicacion_almacen;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
}