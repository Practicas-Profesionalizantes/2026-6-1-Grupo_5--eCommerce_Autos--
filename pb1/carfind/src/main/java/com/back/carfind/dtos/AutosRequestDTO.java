package com.back.carfind.dtos;

import java.time.LocalDate;

public class AutosRequestDTO {

    private Long modelo_id;
    private Double version;
    private Integer anio;
    private Integer kms;
    private Boolean vtv;
    private Boolean grabado;
    private Long combustible_id;
    private Long transmision_id;
    private String color;
    private Integer puertas;
    private String descripcion;
    private LocalDate fecha_creacion;

    // Getters y Setters
    public Long getModelo_id() {
        return modelo_id;
    }

    public void setModelo_id(Long modelo_id) {
        this.modelo_id = modelo_id;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Boolean getVtv() {
        return vtv;
    }

    public void setVtv(Boolean vtv) {
        this.vtv = vtv;
    }

    public Boolean getGrabado() {
        return grabado;
    }

    public void setGrabado(Boolean grabado) {
        this.grabado = grabado;
    }

    public Long getCombustible_id() {
        return combustible_id;
    }

    public void setCombustible_id(Long combustible_id) {
        this.combustible_id = combustible_id;
    }

    public Long getTransmision_id() {
        return transmision_id;
    }

    public void setTransmision_id(Long transmision_id) {
        this.transmision_id = transmision_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}