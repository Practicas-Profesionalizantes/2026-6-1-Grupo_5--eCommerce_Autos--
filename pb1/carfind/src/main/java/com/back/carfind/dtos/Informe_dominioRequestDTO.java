package com.back.carfind.dtos;

import java.time.LocalDate;

public class Informe_dominioRequestDTO {

    private Long id_auto;
    private String estado_dominio;
    private String detalles;
    private LocalDate fecha_informe;

    // Getters y Setters
    public Long getId_auto() {
        return id_auto;
    }

    public void setId_auto(Long id_auto) {
        this.id_auto = id_auto;
    }

    public String getEstado_dominio() {
        return estado_dominio;
    }

    public void setEstado_dominio(String estado_dominio) {
        this.estado_dominio = estado_dominio;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(LocalDate fecha_informe) {
        this.fecha_informe = fecha_informe;
    }
}