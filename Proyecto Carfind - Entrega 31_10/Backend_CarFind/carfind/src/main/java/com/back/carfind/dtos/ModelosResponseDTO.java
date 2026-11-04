package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla modelos.
 */
public class ModelosResponseDTO {

    private Long id;
    private Long marcaId;
    private String nombre;

    public ModelosResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
