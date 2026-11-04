package com.back.carfind.dtos;

import java.math.BigDecimal;

/**
 * Datos que devuelve la API de un registro de la tabla promociones_bancarias.
 */
public class PromocionesBancariasResponseDTO {

    private Long id;
    private Long bancoId;
    private String tarjeta;
    private Integer cuotas;
    private BigDecimal interes;
    private Boolean activa;

    public PromocionesBancariasResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBancoId() {
        return bancoId;
    }

    public void setBancoId(Long bancoId) {
        this.bancoId = bancoId;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
