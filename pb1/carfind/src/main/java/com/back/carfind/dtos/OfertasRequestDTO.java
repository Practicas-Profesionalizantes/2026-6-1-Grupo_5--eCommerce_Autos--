package com.back.carfind.dtos;

import java.time.LocalDate;

public class OfertasRequestDTO {

    private Long id_notificacion;
    private Long id_usuario;
    private String titulo;
    private String mensaje;
    private Boolean leida;
    private LocalDate fecha_creacion;

    // Constructor vacío
    public OfertasRequestDTO() {
    }

    // Constructor con parámetros
    public OfertasRequestDTO(Long id_notificacion, Long id_usuario, String titulo, 
                                     String mensaje, Boolean leida, LocalDate fecha_creacion) {
        this.id_notificacion = id_notificacion;
        this.id_usuario = id_usuario;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.leida = leida;
        this.fecha_creacion = fecha_creacion;
    }

    // Getters y Setters
    public Long getId_notificacion() {
        return id_notificacion;
    }

    public void setId_notificacion(Long id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}