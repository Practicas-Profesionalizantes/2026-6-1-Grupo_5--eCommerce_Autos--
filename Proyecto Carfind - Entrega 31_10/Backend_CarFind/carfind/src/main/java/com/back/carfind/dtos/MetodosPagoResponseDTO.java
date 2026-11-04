package com.back.carfind.dtos;

/**
 * Datos que devuelve la API de un registro de la tabla metodos_pago.
 */
public class MetodosPagoResponseDTO {

    private Long id;
    private Long usuarioId;
    private String datosTokenizados;
    private Boolean activo;
    private Long tipoPagoId;

    public MetodosPagoResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
