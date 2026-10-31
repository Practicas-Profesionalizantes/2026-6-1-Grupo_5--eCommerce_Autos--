package com.back.carfind.dtos;

import java.time.LocalDate;

public class HistorialPreciosResponseDTO {

    private Long id_historial;
    private Long id_publicacion;
    private Float precio_anterior;
    private Float precio_nuevo;
    private LocalDate fecha_cambio;

    // Constructor vacío
    public HistorialPreciosResponseDTO() {
    }

    // Constructor con parámetros
    public HistorialPreciosResponseDTO(Long id_historial, Long id_publicacion, Float precio_anterior, 
                                       Float precio_nuevo, LocalDate fecha_cambio) {
        this.id_historial = id_historial;
        this.id_publicacion = id_publicacion;
        this.precio_anterior = precio_anterior;
        this.precio_nuevo = precio_nuevo;
        this.fecha_cambio = fecha_cambio;
    }

    // Getters y Setters
    public Long getId_historial() {
        return id_historial;
    }

    public void setId_historial(Long id_historial) {
        this.id_historial = id_historial;
    }

    public Long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Float getPrecio_anterior() {
        return precio_anterior;
    }

    public void setPrecio_anterior(Float precio_anterior) {
        this.precio_anterior = precio_anterior;
    }

    public Float getPrecio_nuevo() {
        return precio_nuevo;
    }

    public void setPrecio_nuevo(Float precio_nuevo) {
        this.precio_nuevo = precio_nuevo;
    }

    public LocalDate getFecha_cambio() {
        return fecha_cambio;
    }

    public void setFecha_cambio(LocalDate fecha_cambio) {
        this.fecha_cambio = fecha_cambio;
    }
}