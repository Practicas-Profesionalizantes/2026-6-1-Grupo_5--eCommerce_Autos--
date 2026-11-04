package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla transmisiones (sin el id autogenerado).
 */
public class TransmisionesRequestDTO {

    private String nombre;

    public TransmisionesRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
