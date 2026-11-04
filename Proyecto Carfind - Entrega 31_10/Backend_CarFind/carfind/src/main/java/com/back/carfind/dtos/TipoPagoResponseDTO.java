package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla tipos_pago.
 */
public class TipoPagoResponseDTO {

    private Long id;
    private String nombre;

    public TipoPagoResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
