package com.back.carfind.dtos;

import java.time.LocalDate;

public class DireccionesRequestDTO {

    private String pais;
    private String provincia;
    private String ciudad;
    private String direccion;
    private String tipo;
    private Boolean es_principal;
    private LocalDate fecha_creacion_direccion;

    // Getters y Setters
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEs_principal() {
        return es_principal;
    }

    public void setEs_principal(Boolean es_principal) {
        this.es_principal = es_principal;
    }

    public LocalDate getFecha_creacion_direccion() {
        return fecha_creacion_direccion;
    }

    public void setFecha_creacion_direccion(LocalDate fecha_creacion_direccion) {
        this.fecha_creacion_direccion = fecha_creacion_direccion;
    }
}