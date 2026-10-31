package com.back.carfind.dtos;

import java.time.LocalDate;

public class InventarioResponseDTO {

    private Long id_inventario;
    private Long id_auto;
    private Integer cantidad;
    private String ubicacion_almacen;
    private LocalDate fecha_ingreso;

    // Constructor vacío
    public InventarioResponseDTO() {
    }

    // Constructor con parámetros
    public InventarioResponseDTO(Long id_inventario, Long id_auto, Integer cantidad, 
                                 String ubicacion_almacen, LocalDate fecha_ingreso) {
        this.id_inventario = id_inventario;
        this.id_auto = id_auto;
        this.cantidad = cantidad;
        this.ubicacion_almacen = ubicacion_almacen;
        this.fecha_ingreso = fecha_ingreso;
    }

    // Getters y Setters
    public Long getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(Long id_inventario) {
        this.id_inventario = id_inventario;
    }

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