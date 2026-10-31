package com.back.carfind.dtos;

import java.time.LocalDate;

public class SesionesResponseDTO {

    private Long id_sesion;
    private Long id_usuario;
    private String token;
    private LocalDate fecha_expiracion;
    private Boolean activo;

    // Constructor vacío
    public SesionesResponseDTO() {
    }

    // Constructor con parámetros
    public SesionesResponseDTO(Long id_sesion, Long id_usuario, String token, 
                               LocalDate fecha_expiracion, Boolean activo) {
        this.id_sesion = id_sesion;
        this.id_usuario = id_usuario;
        this.token = token;
        this.fecha_expiracion = fecha_expiracion;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(Long id_sesion) {
        this.id_sesion = id_sesion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(LocalDate fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}