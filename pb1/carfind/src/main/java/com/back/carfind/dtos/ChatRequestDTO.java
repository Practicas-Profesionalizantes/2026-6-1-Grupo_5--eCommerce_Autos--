package com.back.carfind.dtos;

import java.time.LocalDate;

public class ChatRequestDTO {

    private Long id_usuario1;
    private Long id_usuario2;
    private LocalDate fecha_creacion;

    // Getters y Setters
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