package com.back.carfind.dtos;

public class VariantesResponseDTO {

    private Long id_variante;
    private Long id_modelo;
    private String nombre;
    private String descripcion;
    private String anio;

    // Constructor vacío
    public VariantesResponseDTO() {
    }

    // Constructor con parámetros
    public VariantesResponseDTO(Long id_variante, Long id_modelo, String nombre, 
                                String descripcion, String anio) {
        this.id_variante = id_variante;
        this.id_modelo = id_modelo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.anio = anio;
    }

    // Getters y Setters
    public Long getId_variante() {
        return id_variante;
    }

    public void setId_variante(Long id_variante) {
        this.id_variante = id_variante;
    }

    public Long getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Long id_modelo) {
        this.id_modelo = id_modelo;
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

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
