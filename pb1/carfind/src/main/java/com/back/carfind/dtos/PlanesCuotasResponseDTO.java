package com.back.carfind.dtos;

public class PlanesCuotasResponseDTO {

    private Long id_plan_cuota;
    private Long id_financiacion;
    private Integer numero_cuotas;
    private Float tasa_interes;
    private String descripcion;

    // Constructor vacío
    public PlanesCuotasResponseDTO() {
    }

    // Constructor con parámetros
    public PlanesCuotasResponseDTO(Long id_plan_cuota, Long id_financiacion, Integer numero_cuotas, 
                                  Float tasa_interes, String descripcion) {
        this.id_plan_cuota = id_plan_cuota;
        this.id_financiacion = id_financiacion;
        this.numero_cuotas = numero_cuotas;
        this.tasa_interes = tasa_interes;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId_plan_cuota() {
        return id_plan_cuota;
    }

    public void setId_plan_cuota(Long id_plan_cuota) {
        this.id_plan_cuota = id_plan_cuota;
    }

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