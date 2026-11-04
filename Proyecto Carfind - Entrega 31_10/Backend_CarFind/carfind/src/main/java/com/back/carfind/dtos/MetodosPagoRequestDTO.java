package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla metodos_pago (sin el id autogenerado).
 */
public class MetodosPagoRequestDTO {

    private Long usuarioId;
    private String datosTokenizados;
    private Boolean activo;
    private Long tipoPagoId;

    public MetodosPagoRequestDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDatosTokenizados() {
        return datosTokenizados;
    }

    public void setDatosTokenizados(String datosTokenizados) {
        this.datosTokenizados = datosTokenizados;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }
}
