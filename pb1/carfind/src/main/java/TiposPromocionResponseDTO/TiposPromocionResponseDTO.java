package com.back.carfind.dtos;

public class TiposPromocionResponseDTO {

    private Long id_tipo_promocion;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public TiposPromocionResponseDTO() {
    }

    // Constructor con parámetros
    public TiposPromocionResponseDTO(Long id_tipo_promocion, String nombre, String descripcion) {
        this.id_tipo_promocion = id_tipo_promocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId_tipo_promocion() {
        return id_tipo_promocion;
    }

    public void setId_tipo_promocion(Long id_tipo_promocion) {
        this.id_tipo_promocion = id_tipo_promocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
