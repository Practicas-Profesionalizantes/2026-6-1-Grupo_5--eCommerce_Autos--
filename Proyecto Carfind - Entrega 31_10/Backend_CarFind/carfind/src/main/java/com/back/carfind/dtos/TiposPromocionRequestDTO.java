package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla tipos_promocion (sin el id autogenerado).
 */
public class TiposPromocionRequestDTO {

    private String nombre;
    private Integer prioridad;
    private Integer duracionDias;
    private BigDecimal precio;

    public TiposPromocionRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
