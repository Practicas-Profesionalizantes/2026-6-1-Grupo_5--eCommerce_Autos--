package com.back.carfind.dtos;

public class TipoPagoResponseDTO {

    private Long id_tipo_pago;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public TipoPagoResponseDTO() {
    }

    // Constructor con parámetros
    public TipoPagoResponseDTO(Long id_tipo_pago, String nombre, String descripcion) {
        this.id_tipo_pago = id_tipo_pago;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId_tipo_pago() {
        return id_tipo_pago;
    }

    public void setId_tipo_pago(Long id_tipo_pago) {
        this.id_tipo_pago = id_tipo_pago;
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
