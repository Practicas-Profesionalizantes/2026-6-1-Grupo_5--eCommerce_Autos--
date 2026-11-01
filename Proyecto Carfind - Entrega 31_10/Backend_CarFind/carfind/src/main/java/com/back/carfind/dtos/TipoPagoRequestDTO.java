package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla tipos_pago (sin el id autogenerado).
 */
public class TipoPagoRequestDTO {

    private String nombre;

    public TipoPagoRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
