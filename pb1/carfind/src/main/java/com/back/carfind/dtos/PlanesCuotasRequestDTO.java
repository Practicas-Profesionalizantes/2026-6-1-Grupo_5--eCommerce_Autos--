package com.back.carfind.dtos;

public class PlanesCuotasRequestDTO {

    private Long id_financiacion;
    private Integer numero_cuotas;
    private Float tasa_interes;
    private String descripcion;

    // Getters y Setters
    public Long getId_financiacion() {
        return id_financiacion;
    }

    public void setId_financiacion(Long id_financiacion) {
        this.id_financiacion = id_financiacion;
    }

    public Integer getNumero_cuotas() {
        return numero_cuotas;
    }

    public void setNumero_cuotas(Integer numero_cuotas) {
        this.numero_cuotas = numero_cuotas;
    }

    public Float getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(Float tasa_interes) {
        this.tasa_interes = tasa_interes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}