package com.back.carfind.dtos;

public class MetodosPagoResponseDTO {

    private Long id_metodo_pago;
    private String nombre;
    private String descripcion;
    private Boolean activo;

    // Constructor vacío
    public MetodosPagoResponseDTO() {
    }

    // Constructor con parámetros
    public MetodosPagoResponseDTO(Long id_metodo_pago, String nombre, String descripcion, Boolean activo) {
        this.id_metodo_pago = id_metodo_pago;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId_metodo_pago() {
        return id_metodo_pago;
    }

    public void setId_metodo_pago(Long id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}