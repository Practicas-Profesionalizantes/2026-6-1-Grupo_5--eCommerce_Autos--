package com.back.carfind.dtos;

public class VariantesOpcionesRequestDTO {

    private Long id_variante;
    private String nombre_opcion;
    private String valor_opcion;
    private Float precio_adicional;

    // Getters y Setters
    public Long getId_variante() {
        return id_variante;
    }

    public void setId_variante(Long id_variante) {
        this.id_variante = id_variante;
    }

    public String getNombre_opcion() {
        return nombre_opcion;
    }

    public void setNombre_opcion(String nombre_opcion) {
        this.nombre_opcion = nombre_opcion;
    }

    public String getValor_opcion() {
        return valor_opcion;
    }

    public void setValor_opcion(String valor_opcion) {
        this.valor_opcion = valor_opcion;
    }

    public Float getPrecio_adicional() {
        return precio_adicional;
    }

    public void setPrecio_adicional(Float precio_adicional) {
        this.precio_adicional = precio_adicional;
    }
}
