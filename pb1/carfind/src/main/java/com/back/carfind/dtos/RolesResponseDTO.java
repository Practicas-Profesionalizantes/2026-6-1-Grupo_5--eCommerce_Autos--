package com.back.carfind.dtos;

public class RolesResponseDTO {

    private Long id_rol;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public RolesResponseDTO() {
    }

    // Constructor con parámetros
    public RolesResponseDTO(Long id_rol, String nombre, String descripcion) {
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
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