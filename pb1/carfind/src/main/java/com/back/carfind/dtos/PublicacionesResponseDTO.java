package com.back.carfind.dtos;

import java.time.LocalDate;

public class PublicacionesResponseDTO {

    private Long id_publicacion;
    private Long id_auto;
    private Long id_vendedor;
    private String titulo;
    private String descripcion;
    private Float precio;
    private String estado;
    private LocalDate fecha_publicacion;

    // Constructor vacío
    public PublicacionesResponseDTO() {
    }

    // Constructor con parámetros
    public PublicacionesResponseDTO(Long id_publicacion, Long id_auto, Long id_vendedor, String titulo, 
                                    String descripcion, Float precio, String estado, LocalDate fecha_publicacion) {
        this.id_publicacion = id_publicacion;
        this.id_auto = id_auto;
        this.id_vendedor = id_vendedor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.fecha_publicacion = fecha_publicacion;
    }

    // Getters y Setters
    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Long getId_auto() {
        return id_auto;
    }

    public void setId_auto(Long id_auto) {
        this.id_auto = id_auto;
    }

    public Long getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Long id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
}