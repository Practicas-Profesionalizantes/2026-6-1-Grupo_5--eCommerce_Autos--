package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla marcas.
 */
public class MarcasResponseDTO {

    private Long id;
    private String nombre;

    public MarcasResponseDTO() {
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
}
