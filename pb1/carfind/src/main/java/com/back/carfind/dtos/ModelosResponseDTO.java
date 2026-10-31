package com.back.carfind.dtos;

public class ModelosResponseDTO {

    private Long id_modelo;
    private Long id_marca;
    private String nombre;
    private Integer anio_lanzamiento;

    // Constructor vacío
    public ModelosResponseDTO() {
    }

    // Constructor con parámetros
    public ModelosResponseDTO(Long id_modelo, Long id_marca, String nombre, Integer anio_lanzamiento) {
        this.id_modelo = id_modelo;
        this.id_marca = id_marca;
        this.nombre = nombre;
        this.anio_lanzamiento = anio_lanzamiento;
    }

    // Getters y Setters
    public Long getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Long id_modelo) {
        this.id_modelo = id_modelo;
    }

    public Long getId_marca() {
        return id_marca;
    }

    public void setId_marca(Long id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnio_lanzamiento() {
        return anio_lanzamiento;
    }

    public void setAnio_lanzamiento(Integer anio_lanzamiento) {
        this.anio_lanzamiento = anio_lanzamiento;
    }
}