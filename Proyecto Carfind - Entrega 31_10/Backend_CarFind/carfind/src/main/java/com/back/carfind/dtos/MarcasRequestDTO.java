package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla marcas (sin el id autogenerado).
 */
public class MarcasRequestDTO {

    private String nombre;

    public MarcasRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
