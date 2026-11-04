package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla variantes.
 */
public class VariantesResponseDTO {

    private Long id;
    private Long publicacionId;
    private String nombre;

    public VariantesResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
