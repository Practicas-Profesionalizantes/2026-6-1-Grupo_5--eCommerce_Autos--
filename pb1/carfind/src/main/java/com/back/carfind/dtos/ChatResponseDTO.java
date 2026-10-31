package com.back.carfind.dtos;

import java.time.LocalDate;

public class ChatResponseDTO {

    private Long id_chat;
    private Long id_usuario1;
    private Long id_usuario2;
    private LocalDate fecha_creacion;

    // Constructor vacío
    public ChatResponseDTO() {
    }

    // Constructor con parámetros
    public ChatResponseDTO(Long id_chat, Long id_usuario1, Long id_usuario2, LocalDate fecha_creacion) {
        this.id_chat = id_chat;
        this.id_usuario1 = id_usuario1;
        this.id_usuario2 = id_usuario2;
        this.fecha_creacion = fecha_creacion;
    }

    // Getters y Setters
    public Long getId_chat() {
        return id_chat;
    }

    public void setId_chat(Long id_chat) {
        this.id_chat = id_chat;
    }

    public Long getId_usuario1() {
        return id_usuario1;
    }

    public void setId_usuario1(Long id_usuario1) {
        this.id_usuario1 = id_usuario1;
    }

    public Long getId_usuario2() {
        return id_usuario2;
    }

    public void setId_usuario2(Long id_usuario2) {
        this.id_usuario2 = id_usuario2;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}