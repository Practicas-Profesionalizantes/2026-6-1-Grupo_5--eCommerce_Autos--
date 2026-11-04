package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla variante_opciones (sin el id autogenerado).
 */
public class VariantesOpcionesRequestDTO {

    private Long varianteId;
    private String valor;

    public VariantesOpcionesRequestDTO() {
    }

    public Long getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Long varianteId) {
        this.varianteId = varianteId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
