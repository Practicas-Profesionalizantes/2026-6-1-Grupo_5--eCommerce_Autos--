package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que devuelve la API de un registro de la tabla planes_cuotas.
 */
public class PlanesCuotasResponseDTO {

    private Long id;
    private Integer cantidadCuotas;
    private BigDecimal interes;
    private String descripcion;

    public PlanesCuotasResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
