package com.back.carfind.dtos;

/**
 * Datos que recibe la API para crear o actualizar un registro de la tabla tarjetas_usuario (sin el id autogenerado).
 */
public class TarjetasUsuarioRequestDTO {

    private Long usuarioId;
    private String marca;
    private String ultimos4;
    private String token;
    private Integer vencimientoMes;
    private Integer vencimientoAnio;

    public TarjetasUsuarioRequestDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUltimos4() {
        return ultimos4;
    }

    public void setUltimos4(String ultimos4) {
        this.ultimos4 = ultimos4;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getVencimientoMes() {
        return vencimientoMes;
    }

    public void setVencimientoMes(Integer vencimientoMes) {
        this.vencimientoMes = vencimientoMes;
    }

    public Integer getVencimientoAnio() {
        return vencimientoAnio;
    }

    public void setVencimientoAnio(Integer vencimientoAnio) {
        this.vencimientoAnio = vencimientoAnio;
    }
}
