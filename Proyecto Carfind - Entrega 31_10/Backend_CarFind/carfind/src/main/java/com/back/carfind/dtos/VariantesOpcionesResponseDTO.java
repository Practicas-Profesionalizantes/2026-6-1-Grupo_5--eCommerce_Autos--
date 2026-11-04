package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla variante_opciones.
 */
public class VariantesOpcionesResponseDTO {

    private Long id;
    private Long varianteId;
    private String valor;

    public VariantesOpcionesResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
