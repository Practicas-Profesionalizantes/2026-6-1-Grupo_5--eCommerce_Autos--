package com.back.carfind.dtos;

import java.time.LocalDate;

public class HistorialPublicacionesRequestDTO {

    private Long id_chat;
    private Long id_remitente;
    private String mensaje;
    private LocalDate fecha_envio;

    // Getters y Setters
    public Long getId_chat() {
        return id_chat;
    }

    public void setId_chat(Long id_chat) {
        this.id_chat = id_chat;
    }

    public Long getId_remitente() {
        return id_remitente;
    }

    public void setId_remitente(Long id_remitente) {
        this.id_remitente = id_remitente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(LocalDate fecha_envio) {
        this.fecha_envio = fecha_envio;
    }
}