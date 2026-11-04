package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que devuelve la API de un registro de la tabla tipos_promocion.
 */
public class TiposPromocionResponseDTO {

    private Long id;
    private String nombre;
    private Integer prioridad;
    private Integer duracionDias;
    private BigDecimal precio;

    public TiposPromocionResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
