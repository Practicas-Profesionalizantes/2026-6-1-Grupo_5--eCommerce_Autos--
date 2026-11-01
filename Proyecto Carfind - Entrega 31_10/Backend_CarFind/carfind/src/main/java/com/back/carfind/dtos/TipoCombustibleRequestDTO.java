package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla tipos_combustible (sin el id autogenerado).
 */
public class TipoCombustibleRequestDTO {

    private String nombre;

    public TipoCombustibleRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
