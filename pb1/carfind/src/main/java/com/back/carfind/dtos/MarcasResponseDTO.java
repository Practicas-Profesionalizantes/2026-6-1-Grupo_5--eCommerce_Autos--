package com.back.carfind.dtos;

public class MarcasResponseDTO {

    private Long id_marca;
    private String nombre;
    private String pais_origen;

    // Constructor vacío
    public MarcasResponseDTO() {
    }

    // Constructor con parámetros
    public MarcasResponseDTO(Long id_marca, String nombre, String pais_origen) {
        this.id_marca = id_marca;
        this.nombre = nombre;
        this.pais_origen = pais_origen;
    }

    // Getters y Setters
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

    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }
}