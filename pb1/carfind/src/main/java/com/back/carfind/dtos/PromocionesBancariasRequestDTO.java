package com.back.carfind.dtos;

public class PromocionesBancariasRequestDTO {

    private Long id_banco;
    private Long id_promocion;
    private String condiciones;
    private Float descuento_adicional;
    private Boolean activo;

    // Getters y Setters
    public Long getId_banco() {
        return id_banco;
    }

    public void setId_banco(Long id_banco) {
        this.id_banco = id_banco;
    }

    public Long getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(Long id_promocion) {
        this.id_promocion = id_promocion;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public Float getDescuento_adicional() {
        return descuento_adicional;
    }

    public void setDescuento_adicional(Float descuento_adicional) {
        this.descuento_adicional = descuento_adicional;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}