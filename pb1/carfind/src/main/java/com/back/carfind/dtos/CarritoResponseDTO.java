package com.back.carfind.dtos;

public class CarritoResponseDTO {

    private Long id_usuario;
    private Long id_publicacion;
    private Integer cantidad;
    private Float precio_total;

    // Constructor vacío
    public CarritoResponseDTO() {
    }

    // Constructor con parámetros
    public CarritoResponseDTO(Long id_usuario, Long id_publicacion, Integer cantidad, Float precio_total) {
        this.id_usuario = id_usuario;
        this.id_publicacion = id_publicacion;
        this.cantidad = cantidad;
        this.precio_total = precio_total;
    }

    // Getters y Setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(Float precio_total) {
        this.precio_total = precio_total;
    }
}