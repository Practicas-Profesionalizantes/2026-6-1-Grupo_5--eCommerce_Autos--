package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla roles (sin el id autogenerado).
 */
public class RolesRequestDTO {

    private String nombre;
    private Boolean activo;

    public RolesRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
