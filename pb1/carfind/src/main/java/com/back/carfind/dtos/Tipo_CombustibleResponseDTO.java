package com.back.carfind.dtos;

public class Tipo_CombustibleResponseDTO {

    private Long id_tipo_combustible;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public Tipo_CombustibleResponseDTO() {
    }

    // Constructor con parámetros
    public Tipo_CombustibleResponseDTO(Long id_tipo_combustible, String nombre, String descripcion) {
        this.id_tipo_combustible = id_tipo_combustible;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId_tipo_combustible() {
        return id_tipo_combustible;
    }

    public void setId_tipo_combustible(Long id_tipo_combustible) {
        this.id_tipo_combustible = id_tipo_combustible;
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
