package com.back.carfind.dtos;

import java.time.LocalDate;

public class VisitasAutoResponseDTO {

    private Long id_visita;
    private Long id_auto;
    private Long id_usuario;
    private LocalDate fecha_visita;

    // Constructor vacío
    public VisitasAutoResponseDTO() {
    }

    // Constructor con parámetros
    public VisitasAutoResponseDTO(Long id_visita, Long id_auto, Long id_usuario, LocalDate fecha_visita) {
        this.id_visita = id_visita;
        this.id_auto = id_auto;
        this.id_usuario = id_usuario;
        this.fecha_visita = fecha_visita;
    }

    // Getters y Setters
    public Long getId_visita() {
        return id_visita;
    }

    public void setId_visita(Long id_visita) {
        this.id_visita = id_visita;
    }

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

    public LocalDate getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(LocalDate fecha_visita) {
        this.fecha_visita = fecha_visita;
    }
}
