package com.back.carfind.dtos;

public class PromocionesBancariasResponseDTO {

    private Long id_promocion_bancaria;
    private Long id_banco;
    private Long id_promocion;
    private String condiciones;
    private Float descuento_adicional;
    private Boolean activo;

    // Constructor vacío
    public PromocionesBancariasResponseDTO() {
    }

    // Constructor con parámetros
    public PromocionesBancariasResponseDTO(Long id_promocion_bancaria, Long id_banco, Long id_promocion, 
                                          String condiciones, Float descuento_adicional, Boolean activo) {
        this.id_promocion_bancaria = id_promocion_bancaria;
        this.id_banco = id_banco;
        this.id_promocion = id_promocion;
        this.condiciones = condiciones;
        this.descuento_adicional = descuento_adicional;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId_promocion_bancaria() {
        return id_promocion_bancaria;
    }

    public void setId_promocion_bancaria(Long id_promocion_bancaria) {
        this.id_promocion_bancaria = id_promocion_bancaria;
    }

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