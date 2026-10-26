package com.back.carfind.dtos;

import java.time.LocalDate;

public class VerificacionesRequestDTO {

    private Long id_auto;
    private Long id_usuario;
    private String tipo_verificacion;
    private String resultado;
    private String observaciones;
    private LocalDate fecha_verificacion;

    // Getters y Setters
    public Long getId_auto() {
        return id_auto;
    }

    public void setId_auto(Long id_auto) {
        this.id_auto = id_auto;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_verificacion() {
        return tipo_verificacion;
    }

    public void setTipo_verificacion(String tipo_verificacion) {
        this.tipo_verificacion = tipo_verificacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFecha_verificacion() {
        return fecha_verificacion;
    }

    public void setFecha_verificacion(LocalDate fecha_verificacion) {
        this.fecha_verificacion = fecha_verificacion;
    }
}
