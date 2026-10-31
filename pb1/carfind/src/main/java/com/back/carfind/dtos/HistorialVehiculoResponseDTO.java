package com.back.carfind.dtos;

import java.time.LocalDate;

public class HistorialVehiculoResponseDTO {

    private Long id_historial_vehiculo;
    private Long id_auto;
    private String tipo_evento;
    private String descripcion;
    private LocalDate fecha_evento;

    // Constructor vacío
    public HistorialVehiculoResponseDTO() {
    }

    // Constructor con parámetros
    public HistorialVehiculoResponseDTO(Long id_historial_vehiculo, Long id_auto, String tipo_evento, 
                                        String descripcion, LocalDate fecha_evento) {
        this.id_historial_vehiculo = id_historial_vehiculo;
        this.id_auto = id_auto;
        this.tipo_evento = tipo_evento;
        this.descripcion = descripcion;
        this.fecha_evento = fecha_evento;
    }

    // Getters y Setters
    public Long getId_historial_vehiculo() {
        return id_historial_vehiculo;
    }

    public void setId_historial_vehiculo(Long id_historial_vehiculo) {
        this.id_historial_vehiculo = id_historial_vehiculo;
    }

    public Long getId_auto() {
        return id_auto;
    }

    public void setId_auto(Long id_auto) {
        this.id_auto = id_auto;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(LocalDate fecha_evento) {
        this.fecha_evento = fecha_evento;
    }
}