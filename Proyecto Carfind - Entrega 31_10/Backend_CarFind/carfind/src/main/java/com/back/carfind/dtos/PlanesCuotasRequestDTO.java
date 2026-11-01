package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla planes_cuotas (sin el id autogenerado).
 */
public class PlanesCuotasRequestDTO {

    private Integer cantidadCuotas;
    private BigDecimal interes;
    private String descripcion;

    public PlanesCuotasRequestDTO() {
    }

    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(Integer cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
